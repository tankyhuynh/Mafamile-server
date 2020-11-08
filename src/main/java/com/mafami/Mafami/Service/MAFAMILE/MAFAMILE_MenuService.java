package com.mafami.Mafami.Service.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_CategoryEntity;
import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_MenuEntity;
import com.mafami.Mafami.Repository.MAFAMILE.MAFAMILE_MenuRepo;

@Service
public class MAFAMILE_MenuService {

	@Autowired
	private MAFAMILE_MenuRepo mAFAMILE_MenuRepo;

	public MAFAMILE_MenuEntity findOneById(String idProduct) {
		return mAFAMILE_MenuRepo.findOneById(idProduct);
	}

	public List<MAFAMILE_MenuEntity> findAllByCategory(MAFAMILE_CategoryEntity categoryCode) {
		return mAFAMILE_MenuRepo.findAllByCategory(categoryCode);
	}

	public MAFAMILE_MenuEntity findOneByName(String name) {
		return mAFAMILE_MenuRepo.findOneByName(name);
	}

	public List<MAFAMILE_MenuEntity> getAll() {
		return mAFAMILE_MenuRepo.findAll();
	}

	public MAFAMILE_MenuEntity save(MAFAMILE_MenuEntity entity) {
		return mAFAMILE_MenuRepo.save(entity);
	}

	public void delete(String id) {
		mAFAMILE_MenuRepo.delete(mAFAMILE_MenuRepo.findOneById(id));
	}
	
	public void deleteAll() {
		mAFAMILE_MenuRepo.deleteAll();
	}

}
