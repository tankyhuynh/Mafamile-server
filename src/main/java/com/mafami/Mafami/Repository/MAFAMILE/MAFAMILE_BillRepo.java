package com.mafami.Mafami.Repository.MAFAMILE;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_BillEntity;

@Repository
public interface MAFAMILE_BillRepo extends MongoRepository<MAFAMILE_BillEntity, String> {

	MAFAMILE_BillEntity findOneById(String id);
	
}
