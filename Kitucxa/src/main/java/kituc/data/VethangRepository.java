package kituc.data;

import kituc.entity.Vethang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VethangRepository extends JpaRepository<Vethang,Integer> {
    @Query("SELECT vt FROM Vethang vt WHERE vt.xe.id =?1")
    public Vethang findAllByXeId(Integer id);
//    @Query("SELECT vt FROM Vethang vt WHERE vt.bienso LIKE %?2%")
//    public List<Vethang> findAllByBienSo(String bienso);
}
