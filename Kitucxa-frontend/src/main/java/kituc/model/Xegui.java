package kituc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Xegui {

	private int id;

	private float dongia;

	private Date ngaysd;

	private Thanhvien sinhvien;

	private Xe xe;
}
