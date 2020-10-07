package com.mafami.Mafami.Repository.MAFAMILE;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.MAFAMILE.ProductEntity;

@Repository
public interface ProductRepo extends MongoRepository<ProductEntity, String> {

	
	ProductEntity findOneById(String id);
	ProductEntity findOneByCategoryID(String categoryID);
	ProductEntity findOneByName(String name);
	
}
