package kituc.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kituc.entity.Phong;
import kituc.service.PhongService;

@RestController
@RequestMapping(path = "/phong", produces = "application/json")
@CrossOrigin(origins = "*")
public class PhongController {
	@Autowired
	private PhongService phongService;
	
	@GetMapping
	public Iterable<Phong> getAllPhong(){
		return phongService.getAllPhong();
	}
	
	@GetMapping("/{id}")
	public Phong getPhongById(@PathVariable("id") int id) {
		return phongService.getPhongById(id);
	}
	
	@GetMapping("/tim/{keyword}")
	public Iterable<Phong> searchPhong(@PathVariable("keyword") String keyword){
		return phongService.searchPhong(keyword);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Phong themPhong(@RequestBody Phong phong) {
		return phongService.luuPhong(phong);
	}
	
	@PutMapping("/{id}")
	public Phong suaPhong(@RequestBody Phong phong) {
		return phongService.luuPhong(phong);
	}
	
	@DeleteMapping("/{id}")
	public void xoaPhong(@PathVariable("id") int id) {
		phongService.xoaPhong(id);
	}
}
