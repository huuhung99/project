package kituc.web;

import java.util.Arrays;
import java.util.List;

import kituc.model.Thanhvien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/quan-ly-sinh-vien")
public class QuanLySinhvienController {
	RestTemplate rest = new RestTemplate();
	
	@GetMapping
	private String showQuanlySinhvienForm(Model model) {
		List<Thanhvien> listSv = Arrays.asList(rest.getForObject("http://localhost:8080/thanhvien", Thanhvien[].class));
		model.addAttribute("listSv", listSv);
		return "quanlySinhvien";
	}
	
	@GetMapping("/them")
	private String showThemSinhvienForm(Model model) {
		model.addAttribute("sv", new Thanhvien());
		return "sinhvienForm";
	}
	
	@PostMapping("/them")
	private String themSinhvien(@ModelAttribute("sv") Thanhvien sv) {
		sv.setVitri("sinhvien");
		rest.postForObject("http://localhost:8080/thanhvien", sv, Thanhvien.class);
		return "redirect:/quan-ly-sinh-vien";
	}
	
	@GetMapping("/sua/{id}")
	private String showSuaSinhvienForm(Model model, @PathVariable("id") int id) {
		Thanhvien sv =rest.getForObject("http://localhost:8080/thanhvien/{id}", Thanhvien.class, id);
		model.addAttribute("sv", sv);
		return "sinhvienForm";
	}
	
	@PostMapping("/sua/{id}")
	private String suaSinhvien(@ModelAttribute("sv") Thanhvien sv) {
		sv.setVitri("sinhvien");
		rest.put("http://localhost:8080/thanhvien/{id}", sv, sv.getId());
		return "redirect:/quan-ly-sinh-vien";
	}
	
	@GetMapping("/xoa/{id}")
	private String xoaSinhvien(@PathVariable("id") int id) {
		rest.delete("http://localhost:8080/thanhvien/{id}", id);
		return "redirect:/quan-ly-sinh-vien";
	}
	
	@GetMapping("/tim")
	private String timSinhvien(Model model, @RequestParam("keyword") String keyword) {
		List<Thanhvien> listSv = Arrays.asList(rest.getForObject("http://localhost:8080/thanhvien/tim/{keyword}", Thanhvien[].class, keyword));
		model.addAttribute("listSv", listSv);
		return "quanlySinhvien";
	}
	
}
