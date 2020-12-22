package kituc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thanhvien {

	private int id;
	
	private String ten;
	
	private String soCMT;
	
	private String quequan;
	
	private String ngaysinh;
	
	private String vitri;

	private String userName;

	private String password;
	
	private String lop;
}
