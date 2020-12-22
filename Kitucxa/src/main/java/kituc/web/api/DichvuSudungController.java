package kituc.web.api;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kituc.entity.DichvuSudung;
import kituc.service.DichvuSudungService;

@RestController
@RequestMapping(path = "/dichvuSudung", produces = "application/json")
@CrossOrigin(origins = "*")
public class DichvuSudungController {
	@Autowired
	private DichvuSudungService dvsdService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DichvuSudung themDichvuSudung(@RequestBody DichvuSudung dvsd) throws ParseException {
		return dvsdService.luuDvsd(dvsd);
	}
	
	@GetMapping("/sinhvien/{id}")
	public List<DichvuSudung> getDvsdBySinhvienId(@PathVariable("id") int id){
		return dvsdService.getBySinhvienId(id);
	}
}
