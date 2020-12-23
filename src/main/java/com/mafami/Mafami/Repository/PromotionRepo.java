package com.mafami.Mafami.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.PromotionEntity;

@Repository
public interface PromotionRepo extends MongoRepository<PromotionEntity, String> {

	PromotionEntity findOneById(String id);
	
}
