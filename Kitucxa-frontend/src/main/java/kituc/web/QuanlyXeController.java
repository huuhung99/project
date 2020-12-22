package kituc.web;

import kituc.model.Xe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/quan-ly-xe")
public class QuanlyXeController {
	RestTemplate rest = new RestTemplate();
	
	@GetMapping()
	private String showQuanlyXeForm(Model model) {
		List<Xe> listXe = Arrays.asList(rest.getForObject("http://localhost:8080/xe", Xe[].class));
		model.addAttribute("listXe", listXe);
		return "quanlyXe";
	}
	
	@GetMapping("/them")
	private String showThemXeFrom(Model model) {
		model.addAttribute("xe", new Xe());
		return "xeForm";
	}
	
	@PostMapping("/them")
	private String themXe(@ModelAttribute("xe") Xe xe ) {
		rest.postForObject("http://localhost:8080/xe", xe, Xe.class);
		return "redirect:/quan-ly-xe";
	}
	
	@GetMapping("/sua/{id}")
	private String showSuaXeForm(Model model, @PathVariable("id") int id) {
		Xe xe = rest.getForObject("http://localhost:8080/xe/{id}", Xe.class, id);
		model.addAttribute("xe", xe);
		return "xeForm";
	}
	
	@PostMapping("/sua/{id}")
	private String suaPhong(@ModelAttribute("xe") Xe xe) {
		rest.put("http://localhost:8080/xe/{id}", xe, xe.getId());
		return "redirect:/quan-ly-xe";
	}
	
	@GetMapping("/xoa/{id}")
	private String xoaXe(@PathVariable("id") int id) {
		rest.delete("http://localhost:8080/xe/{id}", id);
		return "redirect:/quan-ly-xe";
	}
	
	@GetMapping("/tim")
	private String timXe (Model model, @RequestParam("keyword") String keyword) {
		if(keyword==null||keyword.isEmpty()||keyword.length()==0){
			return "redirect:/quan-ly-xe";
		}
		List<Xe> listXe = Arrays.asList(rest.getForObject("http://localhost:8080/xe/search/{keyword}", Xe[].class, keyword));
		model.addAttribute("listXe", listXe);
		return "quanlyXe";
	}
}
