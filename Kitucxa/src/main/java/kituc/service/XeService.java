package kituc.service;

import kituc.entity.Xe;
import kituc.payload.NewXeRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import kituc.data.XeRepository;

@Service
public class XeService {
    @Autowired
    private XeRepository xeRepository;
    public List<Xe> findAll(){
        return xeRepository.findAll();
    }
    public Xe findById(Integer id){
        Optional<Xe> byId = xeRepository.findById(id);
        if(!byId.isPresent()){
            //throw exception
            return null;
        }
        return byId.get();
    }
    public List<Xe> findAllByBienSo(String bienSo){
//        if(bienSo.isEmpty()){
//            bienSo="";
//        }
        return xeRepository.findAllByBienSo(bienSo);
    }
    public Xe addNew(NewXeRequest newXeRequest){
        Xe xe= xeRepository.findByBienSo(newXeRequest.getBienSo());

        if(xe!=null){
            //throw ex
            return null;
        }
        xe=new Xe();
        xe.setBienSo(newXeRequest.getBienSo());
        xe.setLoaixe(newXeRequest.getLoaixe());
       return xeRepository.save(xe);
    }
    public Xe update(NewXeRequest newXeRequest,int id){
//        Xe xe= xeRepository.findByBienSo(newXeRequest.getBienSo());
//
//        if(xe!=null){
//            //throw ex
//            return null;
//        }
        Xe xe= new Xe();
        xe.setBienSo(newXeRequest.getBienSo());
        xe.setLoaixe(newXeRequest.getLoaixe());
        xe.setId(id);
        return xeRepository.save(xe);
    }
    public void delete(Integer id){
        Optional<Xe> byId = xeRepository.findById(id);
        if(byId.isPresent()){
            xeRepository.deleteById(id);
        }
    }
}
