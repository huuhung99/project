package kituc.service;

import kituc.data.VethangRepository;
import kituc.data.XeRepository;
import kituc.entity.Dichvu;
import kituc.entity.Vethang;
import kituc.entity.Xe;
import kituc.payload.NewVeThangRequest;
import kituc.payload.VeThangRespone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeThangService {
    @Autowired
    private VethangRepository vethangRepository;
    @Autowired
    private XeRepository xeRepository;
    public List<VeThangRespone> findAll(){
        return vethangRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }
    public VeThangRespone findById(Integer id){
        Optional<Vethang> byId = vethangRepository.findById(id);
        if(!byId.isPresent()){
            //throw exception
            return null;
        }
        return this.convert(byId.get());
    }
    public List<VeThangRespone> findByBienSo(String bienSo){
//        if(bienSo.isEmpty()){
//            bienSo="";
//        }
        List<Xe> allByBienSo = xeRepository.findAllByBienSo(bienSo);
        List<Vethang> vethangs= new ArrayList<>();
        if(allByBienSo.isEmpty()){
            // throw ex
            return null;
        }
        for(int i=0;i<allByBienSo.size();++i){
            Vethang allByXeId = vethangRepository.findAllByXeId(allByBienSo.get(i).getId());
            if(allByXeId!=null){
                vethangs.add(allByXeId);
            }
        }
        return vethangs.stream().map(this::convert).collect(Collectors.toList());
    }
    public VeThangRespone addNew(NewVeThangRequest newVeThangRequest){
        Xe xe= xeRepository.findByBienSo(newVeThangRequest.getBienSo());

        if(xe==null){
            //throw ex
            return null;
        }
        // tạo 1 vethang mới để hứng lấy các thuộc tính
        Vethang vethang=new Vethang();
        vethang.setThoigiansd(newVeThangRequest.getThoigiansd());
        vethang.setXe(xe);
        vethangRepository.save(vethang);
        return this.convert(vethang);
    }
    public VeThangRespone update(NewVeThangRequest newVeThangRequest,int id){
        Xe xe= xeRepository.findByBienSo(newVeThangRequest.getBienSo());

        if(xe==null){
            //throw ex
            return null;
        }
        // tạo 1 vethang mới để hứng lấy các thuộc tính
        Vethang vethang=new Vethang();
        vethang.setId(id);
        vethang.setThoigiansd(newVeThangRequest.getThoigiansd());
        vethang.setXe(xe);
        vethangRepository.save(vethang);
        return this.convert(vethang);
    }
    public void delete(Integer id){
        Optional<Vethang> byId = vethangRepository.findById(id);
        if(byId.isPresent()){
            vethangRepository.deleteById(id);
        }
    }
    public VeThangRespone convert(Vethang vethang){
        VeThangRespone veThangRespone =new VeThangRespone();
        veThangRespone.setThoigiansd(vethang.getThoigiansd());
        veThangRespone.setId(vethang.getId());
        Xe xe=xeRepository.findById(vethang.getXe().getId()).get();
        veThangRespone.setXeId(xe.getId());
        veThangRespone.setBienSo(xe.getBienSo());
        return veThangRespone;
    }
}
