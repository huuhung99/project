package kituc.web;

import kituc.model.Thanhvien;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {
    RestTemplate rest = new RestTemplate();
    @GetMapping
    public String show(Model model, HttpSession session){
    	Thanhvien tv = (Thanhvien)session.getAttribute("thanhvien");
    	if(tv!=null) {
    		if(tv.getVitri().equals("admin"))return "trangchuadmin";
    		else {
    			model.addAttribute("sv", tv);
    			return "trangchusinhvien";
    		}
    	}
    	model.addAttribute("thanhvien",new Thanhvien());
        return "login";
    }
    @PostMapping
    public String trangchu(Model model, @ModelAttribute Thanhvien thanhvien, HttpSession session){
        Thanhvien thanhvien1 = rest.postForObject("http://localhost:8080/thanhvien/login", thanhvien, Thanhvien.class);
        if(thanhvien1==null) return"redirect:/";
        if(thanhvien1.getVitri().equals("admin")) {
        	session.setAttribute("thanhvien", thanhvien1);
        	return "trangchuadmin";
        }
        else if(thanhvien1.getVitri().equals("sinhvien")) {
        	session.setAttribute("thanhvien", thanhvien1);
        	model.addAttribute("sv", thanhvien1);
        	return "trangchusinhvien";
        }
        return "login";
    }
    @GetMapping("/dang-xuat")
    private String dangxuat(Model model, HttpSession session) {
    	session.removeAttribute("thanhvien");
    	return "redirect:/";
    }
}
