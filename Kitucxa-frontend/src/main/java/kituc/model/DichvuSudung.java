package kituc.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DichvuSudung {
	
	private int id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date thoigianbd;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date thoigiankt;

	private float dongia;

	private Dichvu dichvu;

	private Thanhvien sinhvien;
}
