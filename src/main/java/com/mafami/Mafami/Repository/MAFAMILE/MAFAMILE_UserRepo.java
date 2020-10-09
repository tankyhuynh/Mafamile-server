package com.mafami.Mafami.Repository.MAFAMILE;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_UserEntity;

public interface MAFAMILE_UserRepo extends MongoRepository<MAFAMILE_UserEntity, String> {

	MAFAMILE_UserEntity findByUsername(String username);
	List<MAFAMILE_UserEntity> findByFullname(String fullname);
	MAFAMILE_UserEntity findOneById(String id);
	MAFAMILE_UserEntity findByUsernameAndPassword(String userName, String password);

	
}
