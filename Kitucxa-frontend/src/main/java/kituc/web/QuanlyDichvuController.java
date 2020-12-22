package kituc.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import kituc.model.Dichvu;

@Controller
@RequestMapping("/quan-ly-dich-vu")
public class QuanlyDichvuController {
	RestTemplate rest = new RestTemplate();
	
	@GetMapping
	private String showQuanlyDichvuFrom(Model model) {
		List<Dichvu> listDv = Arrays.asList(rest.getForObject("http://localhost:8080/dichvu", Dichvu[].class));
		model.addAttribute("listDv", listDv);
		return "quanlyDichvu";
	}
	
	@GetMapping("/them")
	private String showThemDichvuForm(Model model) {
		model.addAttribute("dv", new Dichvu() {});
		return "dichvuForm";
	}
	
	@PostMapping("/them")
	private String themDichvu(@ModelAttribute("dv") Dichvu dv) {
		rest.postForObject("http://localhost:8080/dichvu", dv, Dichvu.class);
		return "redirect:/quan-ly-dich-vu";
	}
	
	@GetMapping("/sua/{id}")
	private String showSuaDichvuForm(Model model, @PathVariable("id") int id) {
		Dichvu dv = rest.getForObject("http://localhost:8080/dichvu/{id}", Dichvu.class, id);
		model.addAttribute("dv", dv);
		return "dichvuForm";
	}
	
	@PostMapping("/sua/{id}")
	private String suaDichvu(@ModelAttribute("dv") Dichvu dv) {
		rest.put("http://localhost:8080/dichvu/{maDv}", dv, dv.getId());
		return "redirect:/quan-ly-dich-vu";
	}
	
	@GetMapping("/xoa/{id}")
	private String xoaDichvu(@PathVariable("id") int id) {
		rest.delete("http://localhost:8080/dichvu/{id}", id);
		return "redirect:/quan-ly-dich-vu";
	}
	
	@GetMapping("/tim")
	private String timDichvu(Model model, @RequestParam("keyword") String keyword) {
		List<Dichvu> listDv = Arrays.asList(rest.getForObject("http://localhost:8080/dichvu/tim/{keyword}", Dichvu[].class, keyword));
		model.addAttribute("listDv", listDv);
		return "quanlyDichvu";
	}
}
