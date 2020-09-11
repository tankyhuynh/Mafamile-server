package com.mafami.Mafami.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.BillDetailEntity;

@Repository
public interface BillDetailRepo extends MongoRepository<BillDetailEntity, String> {

	BillDetailEntity findOneById(String id);
	
}
