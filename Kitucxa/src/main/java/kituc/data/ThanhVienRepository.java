package kituc.data;

import kituc.entity.Thanhvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThanhVienRepository extends JpaRepository<Thanhvien,Integer> {
	@Query("SELECT tv FROM Thanhvien tv WHERE tv.vitri='sinhvien'")
	public List<Thanhvien> findAllSinhvien();
    
	@Query("SELECT tv FROM Thanhvien tv WHERE tv.ten LIKE %?1% AND tv.vitri='sinhvien'")
    public List<Thanhvien> findAllByKey(String keyword);
    
	@Query("SELECT tv FROM Thanhvien tv WHERE tv.userName=?1 AND tv.password=?2")
    public Thanhvien checkLogin(String userName,String password);
}
