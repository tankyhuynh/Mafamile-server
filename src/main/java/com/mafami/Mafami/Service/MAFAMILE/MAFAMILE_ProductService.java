package com.mafami.Mafami.Service.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_ProductEntity;
import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_UserEntity;
import com.mafami.Mafami.Repository.MAFAMILE.MAFAMILE_ProductRepo;

@Service
public class MAFAMILE_ProductService {

	@Autowired
	private MAFAMILE_ProductRepo mAFAMILE_ProductRepo;

	public MAFAMILE_ProductEntity findOneById(String idProduct) {
		return mAFAMILE_ProductRepo.findOneById(idProduct);
	}

	public List<MAFAMILE_ProductEntity> findAllByCategoryCode(String categoryCode) {
		return mAFAMILE_ProductRepo.findAllByCategoryCode(categoryCode);
	}

	public MAFAMILE_ProductEntity findOneByName(String name) {
		return mAFAMILE_ProductRepo.findOneByName(name);
	}

	public List<MAFAMILE_ProductEntity> getAll() {
		return mAFAMILE_ProductRepo.findAll();
	}

	public MAFAMILE_ProductEntity save(MAFAMILE_ProductEntity entity) {
		return mAFAMILE_ProductRepo.save(entity);
	}

	public void delete(String id) {
		mAFAMILE_ProductRepo.delete(mAFAMILE_ProductRepo.findOneById(id));
	}

}
