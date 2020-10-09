package com.mafami.Mafami.Repository.MAFAMILE;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_CustomerEntity;

@Repository
public interface MAFAMILE_CustomerRepo extends MongoRepository<MAFAMILE_CustomerEntity, String> {

	MAFAMILE_CustomerEntity findOneById(String id);
	
}
