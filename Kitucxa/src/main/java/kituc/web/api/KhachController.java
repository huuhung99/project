package kituc.web.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import kituc.entity.Khach;
import kituc.entity.Ngayden;
import kituc.service.KhachService;
import kituc.service.ThanhVienService;

@RestController
@RequestMapping(path="/quanlykhach", produces="application/json" )
@CrossOrigin(origins = "*")
public class KhachController {

	@Autowired
	private KhachService khachSer;
	
	@GetMapping("/laydanhsachkhach/{id}")
	public Iterable<Khach> getKhachBySvID(@PathVariable("id") int id){
		return khachSer.getClientByIdSv(id);
	}
	
	@GetMapping("/tim-khach/{name}&{id}")
	public Iterable<Khach> getKhachByName(@PathVariable("name") String name, @PathVariable("id") int sinhvienid){
		return khachSer.findKhachByName(name, sinhvienid);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Khach LuuKhach(@RequestBody Khach k) {
		return khachSer.save(k);
	}
	
	@PostMapping (path="/luu-ngay-den",consumes ="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Ngayden> luuNgayden(@RequestBody List<Ngayden> n) {
		return khachSer.saveNgayden(n);
	}
	
	@GetMapping("/sua/{id}")
	public Khach getKhachById(@PathVariable("id") int id) {
		return khachSer.findKhachById(id);
	}
	
	@PutMapping("/suakhach/{id}")
	public Khach modifyKhach(@RequestBody Khach khach) {
		return khachSer.save(khach);
	}
	
	@DeleteMapping("/xoa/{id}")
	public void deleteKhachById(@PathVariable("id") int id) {
		khachSer.deleteById(id);
	}
	
	@GetMapping("/dskhach-ngay-den")
	public List<Ngayden> getAllNgayden(){
		return khachSer.getAllNgayden();
	}
	
	@GetMapping("/dskhach-den-trong-khoang/{ngaybd}&{ngaykt}")
	public List<Ngayden> getNgaydenTrongkhoang(@PathVariable("ngaybd") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngaybd, @PathVariable("ngaykt") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date ngaykt){
		return khachSer.getNgaydentrongKhoang(ngaybd, ngaykt);
	}
}
