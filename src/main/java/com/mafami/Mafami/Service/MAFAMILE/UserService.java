package com.mafami.Mafami.Service.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.MAFAMILE.UserEntity;
import com.mafami.Mafami.Repository.MAFAMILE.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public UserEntity findByUsername(String username) {	return userRepo.findByUsername(username); 	}
	
	public List<UserEntity> findByFullname(String fullname) {	return userRepo.findByFullname(fullname); 	}
	
	public UserEntity findOneById(String id) { return userRepo.findOneById(id); }
	
	public UserEntity findByUsernameAndPassword(String username, String password ) {	return userRepo.findByUsernameAndPassword(username, password);	}
	

	public List<UserEntity> getAll() {		return userRepo.findAll();		}

	public UserEntity save(UserEntity entity) {		return userRepo.save(entity);		}
	
	public void delete(String id) {
		userRepo.delete(userRepo.findOneById(id));
	}
	
	

}
