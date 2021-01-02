package com.mafami.Mafami.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.CustomerEntity;

@Repository
public interface CustomerRepo extends MongoRepository<CustomerEntity, String>{

	CustomerEntity findOneById(String id);
	List<CustomerEntity> findAllByName(String name);
	CustomerEntity findOneByPhone(String phone);
}
