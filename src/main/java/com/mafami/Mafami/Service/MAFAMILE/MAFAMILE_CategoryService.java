package com.mafami.Mafami.Service.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_BillEntity;
import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_CategoryEntity;
import com.mafami.Mafami.Repository.MAFAMILE.MAFAMILE_CategoryRepo;

@Service("mafamile")
public class MAFAMILE_CategoryService {

	@Autowired
	private MAFAMILE_CategoryRepo mAFAMILE_CategoryRepo;

	public MAFAMILE_CategoryEntity getOneById(String id) {
		return mAFAMILE_CategoryRepo.findOneById(id);
	}

	public List<MAFAMILE_CategoryEntity> getAll() {
		return mAFAMILE_CategoryRepo.findAll();
	}

	public MAFAMILE_CategoryEntity save(MAFAMILE_CategoryEntity entity) {
		return mAFAMILE_CategoryRepo.save(entity);
	}
	
	public void delete(String id) {
		mAFAMILE_CategoryRepo.deleteById(id);
	}
	
	public void deleteAll() {
		mAFAMILE_CategoryRepo.deleteAll();
	}

}
