package kituc.web;

import kituc.payload.VeThangRespone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/quan-ly-ve-thang")
public class QuanlyVeThangController {
	RestTemplate rest = new RestTemplate();
	@GetMapping()
	private String showQuanlyVeThangForm(Model model) {
		List<VeThangRespone> listVeThang = Arrays.asList(rest.getForObject("http://localhost:8080/vethang", VeThangRespone[].class));
		model.addAttribute("listVeThang", listVeThang);
		return "quanlyVeThang";
	}
	
	@GetMapping("/them")
	private String showThemVeThangFrom(Model model) {
		model.addAttribute("veThang", new VeThangRespone());
		return "VeThangForm";
	}
	
	@PostMapping("/them")
	private String themVeThang(@ModelAttribute("veThang") VeThangRespone veThang ) {
		rest.postForObject("http://localhost:8080/vethang", veThang, VeThangRespone.class);
		return "redirect:/quan-ly-ve-thang";
	}
	
	@GetMapping("/sua/{id}")
	private String showSuaVeThangForm(Model model, @PathVariable("id") int id) {
		VeThangRespone veThangRespone =
				rest.getForObject("http://localhost:8080/vethang/{id}", VeThangRespone.class, id);
//		VeThangRespone veThangRespone= new VeThangRespone();
//		veThangRespone.setId(id);
//		model.addAttribute("veThang", new VeThangRespone());
		model.addAttribute("veThang", veThangRespone);
		return "veThangForm";
	}
	
	@PostMapping("/sua/{id}")
	private String suaVeThang(@ModelAttribute("veThang") VeThangRespone veThang) {
		rest.put("http://localhost:8080/vethang/{id}", veThang, veThang.getId());
		return "redirect:/quan-ly-ve-thang";
	}
	
	@GetMapping("/xoa/{id}")
	private String xoaVeThang(@PathVariable("id") int id) {
		rest.delete("http://localhost:8080/vethang/{id}", id);
		return "redirect:/quan-ly-ve-thang";
	}
	
	@GetMapping("/tim")
	private String timVeThang (Model model, @RequestParam("keyword") String keyword) {
		if(keyword == null|| keyword.isEmpty()|| keyword.length()==0) {
			return "redirect:/quan-ly-ve-thang";
		}
		List<VeThangRespone> listVeThang = Arrays.asList(rest.getForObject("http://localhost:8080/vethang/search/{keyword}", VeThangRespone[].class, keyword));
		model.addAttribute("listVeThang", listVeThang);
		return "quanlyVeThang";
	}
}
