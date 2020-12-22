package kituc.web.api;

import java.util.List;

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

import kituc.entity.Dichvu;
import kituc.service.DichvuService;

@RestController
@RequestMapping(path = "/dichvu", produces = "application/json")
@CrossOrigin(origins = "*")
public class DichvuController {
	@Autowired
	private DichvuService dvService;
	
	@GetMapping
	public List<Dichvu> getAllDichvu() {
		 return dvService.getAllDichvu();
	}
	
	@GetMapping("/{id}")
	public Dichvu getDichvuById(@PathVariable("id") int id) {
		return dvService.getDichvuById(id);
	}
	
	@GetMapping("/tim/{keyword}")
	public List<Dichvu> searchDichvu(@PathVariable("keyword") String keyword){
		return dvService.searchDichvu(keyword);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Dichvu themDichvu(@RequestBody Dichvu dv) {
		return dvService.luuDichvu(dv);
	}
	
	@PutMapping("/{id}")
	public Dichvu suaDichvu(@RequestBody Dichvu dv) {
		return dvService.luuDichvu(dv);
	}
	
	@DeleteMapping("/{id}")
	public void xoaDichvu(@PathVariable("id") int id) {
		dvService.xoaDichvu(id);
	}
}
