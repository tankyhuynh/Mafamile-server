package com.mafami.Mafami.Repository.AMIA;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.AMIA.AMIA_ProductEntity;

@Repository
public interface AMIA_ProductRepo extends MongoRepository<AMIA_ProductEntity, String> {

	AMIA_ProductEntity findOneByCategoryID(String categoryID);

	AMIA_ProductEntity findOneByName(String name);

}
