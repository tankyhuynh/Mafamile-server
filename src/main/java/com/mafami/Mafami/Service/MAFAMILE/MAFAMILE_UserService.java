package com.mafami.Mafami.Service.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_UserEntity;
import com.mafami.Mafami.Repository.MAFAMILE.MAFAMILE_UserRepo;

@Service
public class MAFAMILE_UserService {

	@Autowired
	private MAFAMILE_UserRepo mAFAMILE_UserRepo;

	public MAFAMILE_UserEntity findByUsername(String username) {	return mAFAMILE_UserRepo.findByUsername(username); 	}
	
	public List<MAFAMILE_UserEntity> findByFullname(String fullname) {	return mAFAMILE_UserRepo.findByFullname(fullname); 	}
	
	public MAFAMILE_UserEntity findOneById(String id) { return mAFAMILE_UserRepo.findOneById(id); }
	
	public MAFAMILE_UserEntity findByUsernameAndPassword(String username, String password ) {	return mAFAMILE_UserRepo.findByUsernameAndPassword(username, password);	}
	

	public List<MAFAMILE_UserEntity> getAll() {		return mAFAMILE_UserRepo.findAll();		}

	public MAFAMILE_UserEntity save(MAFAMILE_UserEntity entity) {		return mAFAMILE_UserRepo.save(entity);		}
	
	public void delete(String id) {
		mAFAMILE_UserRepo.delete(mAFAMILE_UserRepo.findOneById(id));
	}
	
	

}
