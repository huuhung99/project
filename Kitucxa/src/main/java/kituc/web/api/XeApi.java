package kituc.web.api;

import kituc.entity.Xe;
import kituc.payload.NewVeThangRequest;
import kituc.payload.NewXeRequest;
import kituc.payload.VeThangRespone;
import kituc.service.VeThangService;
import kituc.service.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/xe", produces = "application/json")
@RestController
@CrossOrigin(origins = "*")
public class XeApi {
    @Autowired
    private XeService xeService;
    @GetMapping
    public List<Xe> findAll(){
        return xeService.findAll();
    }
    @GetMapping("/{id}")
    public Xe findById(@PathVariable Integer id){
        return xeService.findById(id);
    }
    @GetMapping("/search/{xeId}")
    public List<Xe> findByBienSo(@PathVariable String xeId){
        return xeService.findAllByBienSo(xeId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Xe addNew(@RequestBody NewXeRequest newXeRequest){
        return xeService.addNew(newXeRequest);
    }
    @PutMapping("/{id}")
    public Xe update(@RequestBody NewXeRequest newXeRequest,@PathVariable int id){
        return xeService.update(newXeRequest,id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable  Integer id){
        xeService.delete(id);
    }
}
