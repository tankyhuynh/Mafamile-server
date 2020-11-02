package com.mafami.Mafami.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mafami.Mafami.Entity.UserEntity;

public interface UserRepo extends MongoRepository<UserEntity, String> {

	UserEntity findByUsername(String username);
	List<UserEntity> findByFullname(String fullname);
	UserEntity findOneById(String id);
	UserEntity findByUsernameAndPassword(String userName, String password);


	
}
