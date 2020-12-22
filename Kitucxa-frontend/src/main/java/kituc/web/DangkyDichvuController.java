package kituc.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import kituc.model.Dichvu;
import kituc.model.DichvuSudung;
import kituc.model.Thanhvien;

@Controller
@RequestMapping("/dang-ky-dich-vu")
public class DangkyDichvuController {
	RestTemplate rest = new RestTemplate();

	@GetMapping
	private String showFormDangki(Model model) {
		List<Dichvu> listDv = Arrays.asList(rest.getForObject("http://localhost:8080/dichvu", Dichvu[].class));
		model.addAttribute("listDv", listDv);
		model.addAttribute("dvsd", new DichvuSudung());
		return "dangkyDichvu";
	}

	@PostMapping
	private String dangkiDichvu(@ModelAttribute("dvsd") DichvuSudung dvsd, @RequestParam("dv_id") int id, HttpSession session) {
		Dichvu dv = rest.getForObject("http://localhost:8080/dichvu/{id}", Dichvu.class, id);
		Thanhvien sv = (Thanhvien)session.getAttribute("thanhvien");
		dvsd.setSinhvien(sv);
		dvsd.setDichvu(dv);
		rest.postForObject("http://localhost:8080/dichvuSudung", dvsd, DichvuSudung.class);
		return "redirect:/dang-ky-dich-vu";
	}
}
