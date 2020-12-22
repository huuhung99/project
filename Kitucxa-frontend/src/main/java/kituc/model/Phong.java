package kituc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Phong {

	private int id;
	
	private String sophong;
	
	private String loaiphong;
	
	private float dongia;
	
	private int songuoitoida;
	
	private List<Phongthue> phongThue = new ArrayList<>();
}
