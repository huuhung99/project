package kituc.data;

import kituc.entity.Xe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface XeRepository extends JpaRepository<Xe,Integer> {
    @Query("select xe from Xe xe where xe.bienSo like %?1%")
    public List<Xe> findAllByBienSo(String name);
    @Query("select xe from Xe xe where xe.bienSo = ?1")
    public Xe findByBienSo(String name);
}
