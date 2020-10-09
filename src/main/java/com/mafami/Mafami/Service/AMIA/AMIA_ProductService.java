package com.mafami.Mafami.Service.AMIA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.AMIA.AMIA_ProductEntity;
import com.mafami.Mafami.Repository.AMIA.AMIA_ProductRepo;

@Service("amia")
public class AMIA_ProductService {

	@Autowired
	private AMIA_ProductRepo aMIA_ProductRepo;

	public AMIA_ProductEntity findOneById(String idProduct) {
		return aMIA_ProductRepo.findOneById(idProduct);
	}

	public AMIA_ProductEntity findOneByCategoryID(String idCategory) {
		return aMIA_ProductRepo.findOneByCategoryID(idCategory);
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
		aMIA_ProductRepo.delete(aMIA_ProductRepo.findOneById(id));
	}

}
