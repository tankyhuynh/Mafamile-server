package com.mafami.Mafami.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.ComboFoodEntity;

@Repository
public interface ComboFoodRepo extends MongoRepository<ComboFoodEntity, String>{

	ComboFoodEntity findOneById(String id);
	
}
