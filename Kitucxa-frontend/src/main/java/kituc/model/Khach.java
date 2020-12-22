package kituc.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Khach {

	private int id;
	
	private String soCMT;
	
	private String ten;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngaysinh;
	
	private Thanhvien sinhvien;
}
