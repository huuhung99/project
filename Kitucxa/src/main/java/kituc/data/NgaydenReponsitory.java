package kituc.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kituc.entity.Ngayden;

public interface NgaydenReponsitory extends JpaRepository<Ngayden, Integer>{

	@Query("SELECT n FROM Ngayden n WHERE ngayden BETWEEN ?1 AND ?2")
	public List<Ngayden> dsNgayden(Date ngaybd,Date ngaykt);

}
