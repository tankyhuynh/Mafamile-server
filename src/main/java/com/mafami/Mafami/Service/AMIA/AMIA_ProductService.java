package com.mafami.Mafami.Service.AMIA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.AMIA.AMIA_ProductEntity;
import com.mafami.Mafami.Repository.AMIA.AMIA_ProductRepo;

@Service
public class AMIA_ProductService {

	@Autowired
	private AMIA_ProductRepo aMIA_ProductRepo;

	public AMIA_ProductEntity findOneById(String idProduct) {
		return aMIA_ProductRepo.findById(idProduct).orElse(new AMIA_ProductEntity());
	}

	public List<AMIA_ProductEntity> findAllByCategoryCode(String categoryCode) {
		return aMIA_ProductRepo.findAllByCategoryCode(categoryCode);
	}

	public AMIA_ProductEntity findOneByName(String name) {
		return aMIA_ProductRepo.findOneByName(name);
	}

	public List<AMIA_ProductEntity> getAll() {
		return aMIA_ProductRepo.findAll();
	}

	public AMIA_ProductEntity save(AMIA_ProductEntity entity) {
		return aMIA_ProductRepo.save(entity);
	}

	public void delete(String id) {
		aMIA_ProductRepo.deleteById(id);
	}
	
	public void deleteAll() {
		aMIA_ProductRepo.deleteAll();
	}

}
