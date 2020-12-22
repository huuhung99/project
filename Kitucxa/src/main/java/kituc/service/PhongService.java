package kituc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kituc.data.PhongRepository;
import kituc.entity.Phong;

@Service
public class PhongService {
	@Autowired
	private PhongRepository phongRepo;
	
	public Iterable<Phong> getAllPhong(){
		return phongRepo.findAll();
	}
	
	public Phong getPhongById(int id) {
		Optional<Phong> optPhong = phongRepo.findById(id);
		if(optPhong.isPresent()) {
			return optPhong.get();
		}
		return null;
	}
	
	public Iterable<Phong> searchPhong(String keyword){
		return phongRepo.findAll(keyword);
	}
	
	public Phong luuPhong(Phong phong) {
		return phongRepo.save(phong);
	}
	
	public void xoaPhong(int id) {
		phongRepo.deleteById(id);
	}
}
