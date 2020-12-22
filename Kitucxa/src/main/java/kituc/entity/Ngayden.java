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

import lombok.Data;

@Data
@Entity
@Table(name="tblngayden")
public class Ngayden {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="ngayden")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayden;
	
	@ManyToOne
	@JoinColumn(name="khachid")
	private Khach khach;	
	
}
