package com.mafami.Mafami.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.UserEntity;

@Repository
public interface User_Repo extends MongoRepository<UserEntity, String> {

	UserEntity findByUserName(String userName);
	UserEntity findOneById(String id);
	UserEntity findByUserNameAndPassword(String userName, String password);

	
}
