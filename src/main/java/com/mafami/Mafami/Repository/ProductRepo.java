package com.mafami.Mafami.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.ProductEntity;

@Repository
public interface ProductRepo extends MongoRepository<ProductEntity, String> {

	ProductEntity findOneByName(String name);
	ProductEntity findOneById(String id);
	
}
