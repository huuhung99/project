package kituc.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data

public class Ngayden {
	
	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayden;
	
	private Khach khach;	
	
}
