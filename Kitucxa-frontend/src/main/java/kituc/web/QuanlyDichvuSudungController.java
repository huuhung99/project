package kituc.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import kituc.model.DichvuSudung;
import kituc.model.Thanhvien;

@Controller
@RequestMapping("/quan-ly-dich-vu-su-dung")
public class QuanlyDichvuSudungController {
	RestTemplate rest = new RestTemplate();
	
	@GetMapping
	private String showQuanlyDichvuSudugnForm() {
		return "quanlyDichvuSudung";
	}
	
	@GetMapping("/tim-sinh-vien")
	private String timSinhvien(Model model, @RequestParam("keyword") String keyword) {
		List<Thanhvien> listSv = Arrays.asList(rest.getForObject("http://localhost:8080/thanhvien/tim/{keyword}", Thanhvien[].class, keyword));
		model.addAttribute("listSv", listSv);
		return "quanlyDichvuSudung";
	}
	
	@GetMapping("/xem-dich-vu-su-dung")
	private String showDichvuSudungForSinhvien(Model model, @RequestParam("sinhvien") int id, @RequestParam("sDate") String sdate, @RequestParam("eDate") String edate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = formatter.parse(sdate);
		Date eDate = formatter.parse(edate);
		List<DichvuSudung> listDvsd = Arrays.asList(rest.getForObject("http://localhost:8080/dichvuSudung/sinhvien/{id}", DichvuSudung[].class, id));
		Thanhvien sv = rest.getForObject("http://localhost:8080/thanhvien/{id}", Thanhvien.class, id);
		model.addAttribute("sv", sv);
		List<String> listDv = new ArrayList<>();
		for(DichvuSudung dvsd : listDvsd) {
			Calendar cal1 = Calendar.getInstance(); 
		    Calendar cal2 = Calendar.getInstance(); 
		    cal1.setTime(sDate); 
		    cal2.setTime(eDate);
		    Calendar cal3 = Calendar.getInstance(); 
		    Calendar cal4 = Calendar.getInstance(); 
		    cal3.setTime(dvsd.getThoigianbd()); 
		    cal4.setTime(dvsd.getThoigiankt());
		    int daysBetween1 = cal3.get(Calendar.DAY_OF_YEAR) - cal2.get(Calendar.DAY_OF_YEAR);
		    int daysBetween2 = cal4.get(Calendar.DAY_OF_YEAR) - cal1.get(Calendar.DAY_OF_YEAR);
		    if(daysBetween1<0 && daysBetween2>0) {
		    	if(listDv.contains(dvsd.getDichvu().getTen())==false) {
					listDv.add(dvsd.getDichvu().getTen());
				}
		    }
		}
		List<DvSdModel> listModel = new ArrayList<>();
		for(String dv : listDv) {
			DvSdModel dvsdM = new DvSdModel();
			dvsdM.setTen(dv);
			listModel.add(dvsdM);
		}
		for(String dv : listDv) {
			for(DichvuSudung dvsd : listDvsd) {
				if(dvsd.getDichvu().getTen().equals(dv)) {
					Calendar cal1 = Calendar.getInstance(); 
				    Calendar cal2 = Calendar.getInstance(); 
				    cal1.setTime(sDate); 
				    cal2.setTime(dvsd.getThoigianbd()); 

				    int daysBetween1 = cal2.get(Calendar.DAY_OF_YEAR) - cal1.get(Calendar.DAY_OF_YEAR);
				    if(daysBetween1<0) {
				    	Calendar cal3 = Calendar.getInstance(); 
					    Calendar cal4 = Calendar.getInstance(); 
					    cal3.setTime(eDate); 
					    cal4.setTime(dvsd.getThoigiankt()); 

					    int daysBetween2 = cal4.get(Calendar.DAY_OF_YEAR) - cal3.get(Calendar.DAY_OF_YEAR);
					    if(daysBetween2<0) {
					    	int totalDaySd = cal4.get(Calendar.DAY_OF_YEAR) - cal1.get(Calendar.DAY_OF_YEAR);
					    	for(DvSdModel dvsdM : listModel) {
					    		if(dvsdM.getTen().equals(dv)) {
					    			dvsdM.setIncome(totalDaySd*dvsd.getDongia());
					    		}
					    	}
					    }
					    else {
					    	int totalDaySd = cal3.get(Calendar.DAY_OF_YEAR) - cal1.get(Calendar.DAY_OF_YEAR);
					    	for(DvSdModel dvsdM : listModel) {
					    		if(dvsdM.getTen().equals(dv)) {
					    			dvsdM.setIncome(totalDaySd*dvsd.getDongia());
					    		}
					    	}
					    }
				    }
				    else {
				    	Calendar cal3 = Calendar.getInstance(); 
					    Calendar cal4 = Calendar.getInstance(); 
					    cal3.setTime(eDate); 
					    cal4.setTime(dvsd.getThoigiankt()); 

					    int daysBetween2 = cal4.get(Calendar.DAY_OF_YEAR) - cal3.get(Calendar.DAY_OF_YEAR);
					    if(daysBetween2<0) {
					    	int totalDaySd = cal4.get(Calendar.DAY_OF_YEAR) - cal2.get(Calendar.DAY_OF_YEAR);
					    	for(DvSdModel dvsdM : listModel) {
					    		if(dvsdM.getTen().equals(dv)) {
					    			dvsdM.setIncome(totalDaySd*dvsd.getDongia());
					    		}
					    	}
					    }
					    else {
					    	int totalDaySd = cal3.get(Calendar.DAY_OF_YEAR) - cal2.get(Calendar.DAY_OF_YEAR);
					    	for(DvSdModel dvsdM : listModel) {
					    		if(dvsdM.getTen().equals(dv)) {
					    			dvsdM.setIncome(totalDaySd*dvsd.getDongia());
					    		}
					    	}
					    }
				    }
				}
			}
		}
		model.addAttribute("sDate", sdate);
		model.addAttribute("eDate", edate);
		model.addAttribute("listModel", listModel);
		return "dichvuSudungForSinhvien";
	}
	
	private class DvSdModel{
		private String ten;
		private float income;
		public DvSdModel() {
		}
		public String getTen() {
			return ten;
		}
		public float getIncome() {
			return income;
		}
		public void setTen(String ten) {
			this.ten = ten;
		}
		public void setIncome(float income) {
			this.income = income;
		}
		
	}
}
