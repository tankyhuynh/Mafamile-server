package com.mafami.Mafami.Service.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.AMIA.AMIA_BillEntity;
import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_BillEntity;
import com.mafami.Mafami.Repository.MAFAMILE.MAFAMILE_BillRepo;

@Service
public class MAFAMILE_BillService {

	@Autowired
	private MAFAMILE_BillRepo mAFAMILE_BillRepo;

	public MAFAMILE_BillEntity getOneById(String id) {
		return mAFAMILE_BillRepo.findOneById(id);
	}

	public List<MAFAMILE_BillEntity> getAll() {
		return mAFAMILE_BillRepo.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
	}

	public MAFAMILE_BillEntity save(MAFAMILE_BillEntity entity) {
		return mAFAMILE_BillRepo.save(entity);
	}

	public void delete(String id) {
		mAFAMILE_BillRepo.deleteById(id);
	}

	public void deleteAll() {
		mAFAMILE_BillRepo.deleteAll();
	}

}
