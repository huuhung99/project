package kituc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tblkhach")
public class Khach {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name ="soCMT")
	private String soCMT;
	
	@Column(name="ten")
	private String ten;
	
	@Column(name="ngaysinh")
	@DateTimeFormat(pattern = "yyyy-MM-đ")
	private Date ngaysinh;
		
	// quan hệ n-1 với sinh viên
	@ManyToOne
	@JoinColumn(name="thanhvienid")
	private Thanhvien sinhvien;
}
