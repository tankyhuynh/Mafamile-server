package com.mafami.Mafami.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.ProductEntity;

@Repository
public interface ProductRepo extends MongoRepository<ProductEntity, String> {

	
	ProductEntity findOneById(String id);
	ProductEntity findOneByIdcategory(String idCategory);
	ProductEntity findOneByName(String name);
	
}
