package kituc.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kituc.data.DichvuSudungRepository;
import kituc.entity.DichvuSudung;

@Service
public class DichvuSudungService {
	@Autowired
	private DichvuSudungRepository dvsdRepo;
	
	public DichvuSudung luuDvsd(DichvuSudung dvsd) throws ParseException {
		dvsd.setDongia(dvsd.getDichvu().getDongia());
		return dvsdRepo.save(dvsd);
	}
	
	public List<DichvuSudung> getBySinhvienId(int id){
		return dvsdRepo.findBySinhvienId(id);
	}
}
