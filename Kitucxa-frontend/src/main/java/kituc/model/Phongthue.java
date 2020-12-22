package kituc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Phongthue {

	private int id;
	
	private float dongia;
	
	private Date thoigianbd;
	
	private Date thoigiankt;
	
	private Phong phong;
	
	private Thanhvien sinhvien;
}
