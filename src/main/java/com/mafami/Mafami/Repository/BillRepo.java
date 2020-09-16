package com.mafami.Mafami.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.BillEntity;

@Repository
public interface BillRepo extends MongoRepository<BillEntity, String> {

	BillEntity findOneById(String id);
	BillEntity findOneByIdproduct(String idProduct);
	BillEntity findOneByIdcustomer(String idCustomer);
	
}
