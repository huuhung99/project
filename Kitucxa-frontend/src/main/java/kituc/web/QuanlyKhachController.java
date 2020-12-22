package kituc.web;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import kituc.model.Khach;
import kituc.model.Ngayden;
import kituc.model.Thanhvien;

@Controller
@RequestMapping("/quanlykhach")
public class QuanlyKhachController {
	
	private RestTemplate rest = new RestTemplate();
	@Autowired
	private HttpSession session;
	
	@GetMapping
	public String showQLkhach(Model model) {
		List<Thanhvien> ListSv = Arrays.asList(rest.getForObject("http://localhost:8080/thanhvien", Thanhvien[].class) );
		model.addAttribute("sinhviens",ListSv);
		return "QLKhachdenchoi";
	}
	
	@GetMapping("/timkiem")
	private String timSinhvien(Model model, @RequestParam("txtSearch") String keyword) {
		List<Thanhvien> listSv = Arrays.asList(rest.getForObject("http://localhost:8080/thanhvien/tim/{keyword}", Thanhvien[].class, keyword));
		model.addAttribute("sinhviens", listSv);
		return "QLKhachdenchoi";
	} 
	
	@GetMapping("/dskhach")
	public String showKhachTheoIdSV(@RequestParam("chon") int id, Model model ) throws ParseException{
		List<Khach> listKhach = Arrays.asList(rest.getForObject("http://localhost:8080/quanlykhach/laydanhsachkhach/{id}", Khach[].class, id));
		session.setAttribute("id", id);
		model.addAttribute("khachs", listKhach);
		return "QLDSkhach";
	}
	
	@GetMapping("/them")
	public String getluuKhach(Model model) {
		model.addAttribute("khach", new Khach());
		return "ThemKhachDen";
	}
	
	@GetMapping("/tim-khach")
	public String timKhach(Model model, @RequestParam("txtSearchClient") String key) {
		int id = (int) session.getAttribute("id");
		List<Khach> arrKhach = Arrays.asList(rest.getForObject("http://localhost:8080/quanlykhach/tim-khach/{name}&{id}", Khach[].class, key, id));
		model.addAttribute("khachs", arrKhach);
		return "QLDSkhach";
	}
	
	@PostMapping("/luu-khach")
	public String luuKhach(@ModelAttribute("khach") Khach khach, @RequestParam("ngaysinh") String ngaysinh) throws ParseException {
		int id = (int) session.getAttribute("id");
		Thanhvien sinhvien = new Thanhvien();
		sinhvien.setId(id);
		khach.setSinhvien(sinhvien);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		khach.setNgaysinh(sdf.parse(ngaysinh));
		rest.postForObject("http://localhost:8080/quanlykhach", khach , Khach.class);
		return "redirect:/quanlykhach/dskhach?chon="+id;
	}
	
	@PostMapping("/luu-ngay-den-choi")
	public String luuNgay(@RequestParam("ngayden") String ngay, @RequestParam("chon") List<String> ArridKhach) throws ParseException {			
		
		List<Ngayden> arrNgayden = new ArrayList<Ngayden>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date ngaydenchoi = sdf.parse(ngay);
		for(int i=0; i<ArridKhach.size(); i++) {
			Khach khach = new Khach();
			Ngayden ngayden = new Ngayden();
			khach.setId(Integer.parseInt(ArridKhach.get(i)));
			ngayden.setKhach(khach);
			ngayden.setNgayden(ngaydenchoi);
			arrNgayden.add(ngayden);
		}		
		rest.postForObject("http://localhost:8080/quanlykhach/luu-ngay-den", arrNgayden , arrNgayden.getClass());
		return "redirect:/quanlykhach";
	}
	
	@GetMapping("/sua/{id}")
	public String getKhachToSua(Model model, @PathVariable("id") int id) {
		Khach khach = rest.getForObject("http://localhost:8080/quanlykhach/sua/{id}", Khach.class, id);
		session.setAttribute("idkhach", khach.getId());
		model.addAttribute("khach", khach);
		return "SuaKhach";
	}
	
	@PostMapping("/suakhach")
	public String suaKhach(@ModelAttribute("khach") Khach khach) {
		int id = (int) session.getAttribute("id");
		int idkhach = (int) session.getAttribute("idkhach");
		Thanhvien sinhvien = new Thanhvien();
		sinhvien.setId(id);
		khach.setId(idkhach);
		khach.setSinhvien(sinhvien);
		rest.put("http://localhost:8080/quanlykhach/suakhach/{id}", khach , idkhach );
		return "redirect:/quanlykhach/dskhach?chon="+id;
	}
	
	@GetMapping("/xoa/{id}")
	public String xoaKhach(@PathVariable("id") int id) {
		int idsv = (int) session.getAttribute("id");
		rest.delete("http://localhost:8080/quanlykhach/xoa/{id}", id);
		return "redirect:/quanlykhach/dskhach?chon="+idsv;
	}
	
	@GetMapping("/quaylai")
	public String quayLai() {
		int id = (int) session.getAttribute("id");
		return "redirect:/quanlykhach/dskhach?chon="+id;
	}
	
	@GetMapping("/dskhachdenchoi")
	public String showDSkhachdenchoi(Model model) {
		List<Ngayden> listngay = Arrays.asList(rest.getForObject("http://localhost:8080/quanlykhach/dskhach-ngay-den", Ngayden[].class));
		model.addAttribute("ngaydens", listngay);
		return "inDsKhachTheoThoigian";
	}
	
	@GetMapping("/dskhach-den-choi-trong-thoigian")
	public String getDSfortime(Model model, @RequestParam("thoigianbd") String thoigianbd, @RequestParam("thoigiankt") String thoigiankt){
		List<Ngayden> listngay = Arrays.asList(rest.getForObject("http://localhost:8080/quanlykhach/dskhach-den-trong-khoang/{ngaybd}&{ngaykt}", Ngayden[].class, thoigianbd, thoigiankt));
		model.addAttribute("ngaydens", listngay);
		return "inDSKhachTheoThoigian";
	}
}
