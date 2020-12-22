package kituc.data;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kituc.entity.Khach;
import kituc.entity.Ngayden;

public interface KhachReponsitory extends JpaRepository<Khach, Integer>{
	
	@Query("SELECT k FROM Khach k WHERE thanhvienid = ?1")
	public List<Khach> getAllClientBySvId(int svId);
	
	@Query("SELECT k FROM Khach k WHERE k.ten LIKE %?1% AND thanhvienid= ?2")
	public List<Khach> findKhachByName(String name, int sinhvienid);
	
}
















