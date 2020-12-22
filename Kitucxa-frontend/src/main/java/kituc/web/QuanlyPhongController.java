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

import kituc.model.Phong;

@Controller
@RequestMapping("/quan-ly-phong")
public class QuanlyPhongController {
	RestTemplate rest = new RestTemplate();
	
	@GetMapping()
	private String showQuanlyPhongForm(Model model) {
		List<Phong> listPhong = Arrays.asList(rest.getForObject("http://localhost:8080/phong", Phong[].class));
		model.addAttribute("listPhong", listPhong);
		return "quanlyPhong";
	}
	
	@GetMapping("/them")
	private String showThemPhongFrom(Model model) {
		model.addAttribute("phong", new Phong());
		return "phongForm";
	}
	
	@PostMapping("/them")
	private String themPhong(@ModelAttribute("phong") Phong phong ) {
		rest.postForObject("http://localhost:8080/phong", phong, Phong.class);
		return "redirect:/quan-ly-phong";
	}
	
	@GetMapping("/sua/{id}")
	private String showSuaPhongForm(Model model, @PathVariable("id") int id) {
		Phong phong = rest.getForObject("http://localhost:8080/phong/{id}", Phong.class, id);
		model.addAttribute("phong", phong);
		return "phongForm";
	}
	
	@PostMapping("/sua/{id}")
	private String suaPhong(@ModelAttribute("phong") Phong phong) {
		rest.put("http://localhost:8080/phong/{id}", phong, phong.getId());
		return "redirect:/quan-ly-phong";
	}
	
	@GetMapping("/xoa/{id}")
	private String xoaPhong(@PathVariable("id") int id) {
		rest.delete("http://localhost:8080/phong/{id}", id);
		return "redirect:/quan-ly-phong";
	}
	
	@GetMapping("/tim")
	private String timPhong (Model model, @RequestParam("keyword") String keyword) {
		List<Phong> listPhong = Arrays.asList(rest.getForObject("http://localhost:8080/phong/tim/{keyword}", Phong[].class, keyword));
		model.addAttribute("listPhong", listPhong);
		return "quanlyPhong";
	}
}
