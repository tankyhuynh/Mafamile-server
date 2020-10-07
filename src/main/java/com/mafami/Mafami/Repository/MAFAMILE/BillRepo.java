package com.mafami.Mafami.Repository.MAFAMILE;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.MAFAMILE.BillEntity;

@Repository
public interface BillRepo extends MongoRepository<BillEntity, String> {

	BillEntity findOneById(String id);
	BillEntity findOneByProductID(String productID);
	BillEntity findOneByCustomerID(String customerID);
	
}
