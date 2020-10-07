package com.mafami.Mafami.Repository.MAFAMILE;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.MAFAMILE.CustomerEntity;

@Repository
public interface CustomerRepo extends MongoRepository<CustomerEntity, String> {

	CustomerEntity findOneById(String id);
	
}