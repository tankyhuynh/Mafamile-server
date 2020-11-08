package com.mafami.Mafami.Service.AMIA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.AMIA.AMIA_CategoryEntity;
import com.mafami.Mafami.Entity.AMIA.AMIA_MenuEntity;
import com.mafami.Mafami.Repository.AMIA.AMIA_MenuRepo;

@Service
public class AMIA_MenuService {

	@Autowired
	private AMIA_MenuRepo aMIA_MenuRepo;

	public AMIA_MenuEntity findOneById(String idProduct) {
		return aMIA_MenuRepo.findById(idProduct).orElse(new AMIA_MenuEntity());
	}

	public List<AMIA_MenuEntity> findAllByCategoryCode(AMIA_CategoryEntity categoryCode) {
		return aMIA_MenuRepo.findAllByCategory(categoryCode);
	}

	public AMIA_MenuEntity findOneByName(String name) {
		return aMIA_MenuRepo.findOneByName(name);
	}

	public List<AMIA_MenuEntity> getAll() {
		return aMIA_MenuRepo.findAll();
	}

	public AMIA_MenuEntity save(AMIA_MenuEntity entity) {
		return aMIA_MenuRepo.save(entity);
	}

	public void delete(String id) {
		aMIA_MenuRepo.deleteById(id);
	}
	
	public void deleteAll() {
		aMIA_MenuRepo.deleteAll();
	}

}
