package kituc.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import kituc.entity.Phong;
@EnableJpaRepositories
public interface PhongRepository extends JpaRepository<Phong, Integer>{
	@Query("SELECT p FROM Phong p WHERE p.sophong LIKE %?1%")
	public List<Phong> findAll(String keyword);
}
