package kituc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vethang{
		
	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date thoigiansd;
	
	private Xe xe;
}
