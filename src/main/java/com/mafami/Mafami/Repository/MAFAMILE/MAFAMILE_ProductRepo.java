package com.mafami.Mafami.Repository.MAFAMILE;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_ProductEntity;

@Repository
public interface MAFAMILE_ProductRepo extends MongoRepository<MAFAMILE_ProductEntity, String> {

	
	MAFAMILE_ProductEntity findOneById(String id);
	MAFAMILE_ProductEntity findOneByCategoryID(String categoryID);
	MAFAMILE_ProductEntity findOneByName(String name);
	
}
