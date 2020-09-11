package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.UserEntity;
import com.mafami.Mafami.Repository.User_Repo;

@Service
public class User_Service {

	@Autowired
	private User_Repo user_Repo;

	public UserEntity get(String userName) {	return user_Repo.findByUserName(userName); 	}
	
	public UserEntity findOneById(String id) {
		return user_Repo.findOneById(id);
	}

	public List<UserEntity> getAll() {		return user_Repo.findAll();		}

	public UserEntity save(UserEntity entity) {		return user_Repo.save(entity);		}
	
	public void delete(String id) {
		user_Repo.delete(user_Repo.findOneById(id));
	}
	
	public UserEntity findByUserNameAndPassword(String userName, String password ) {
		return user_Repo.findByUserNameAndPassword(userName, password);
	}

}
