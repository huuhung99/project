package kituc.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kituc.entity.Dichvu;

public interface DichvuRepository extends JpaRepository<Dichvu, Integer>{
	@Query("SELECT dv FROM Dichvu dv WHERE dv.ten LIKE %?1%")
	public List<Dichvu> findAll(String keyword);
}
