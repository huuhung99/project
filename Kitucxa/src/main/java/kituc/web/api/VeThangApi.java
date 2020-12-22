package kituc.web.api;

import kituc.payload.NewVeThangRequest;
import kituc.payload.VeThangRespone;
import kituc.service.VeThangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/vethang", produces = "application/json")
@RestController
@CrossOrigin(origins = "*")
public class VeThangApi {
    @Autowired
    private VeThangService veThangService;
    @GetMapping
    public List<VeThangRespone> findAll(){
        return veThangService.findAll();
    }
    @GetMapping("/{id}")
    public VeThangRespone findById(@PathVariable Integer id){
        return veThangService.findById(id);
    }
    @GetMapping("/search/{xeId}")
    public List<VeThangRespone> findByBienSo(@PathVariable String xeId){
        return veThangService.findByBienSo(xeId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeThangRespone addNew(@RequestBody NewVeThangRequest vethang){
        return veThangService.addNew(vethang);
    }
    @PutMapping("/{id}")
    public VeThangRespone update(@RequestBody NewVeThangRequest vethang,@PathVariable int id){
        return veThangService.update(vethang,id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable  Integer id){
        veThangService.delete(id);
    }
}
