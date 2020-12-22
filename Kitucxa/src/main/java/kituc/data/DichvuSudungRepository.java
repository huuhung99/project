package kituc.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kituc.entity.DichvuSudung;

public interface DichvuSudungRepository extends CrudRepository<DichvuSudung, Integer>{
	@Query("SELECT dvsd FROM DichvuSudung dvsd WHERE dvsd.sinhvien.id = ?1")
	public List<DichvuSudung> findBySinhvienId(int id);
}
