package kituc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbldichvusudung")
public class DichvuSudung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name ="thoigianbd")
	private Date thoigianbd;
	
	@Column(name ="thoigiankt")
	private Date thoigiankt;
	
	@Column(name="dongia")
	private float dongia;
	
	@ManyToOne
	@JoinColumn(name = "dichvuid")
	private Dichvu dichvu;
	
	@ManyToOne
	@JoinColumn(name = "thanhVienId")
	private Thanhvien sinhvien;

	@PrePersist
	void thoigianbd() {
		this.thoigianbd = new Date();
	}
}
