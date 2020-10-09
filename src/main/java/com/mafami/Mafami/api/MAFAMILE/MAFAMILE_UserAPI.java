package com.mafami.Mafami.api.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Convert.MAFAMILE.UserConvert;
import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_UserEntity;
import com.mafami.Mafami.Service.MAFAMILE.MAFAMILE_UserService;

@RestController
@RequestMapping("/api/mafamile/user")
public class MAFAMILE_UserAPI {

	@Autowired
	private MAFAMILE_UserService mAFAMILE_UserService;
	
	@Autowired
	private UserConvert userConvert;
	
	@GetMapping
	public List<MAFAMILE_UserEntity> getAll() {
		return mAFAMILE_UserService.getAll();
	}

	@GetMapping("/{id}")
	public MAFAMILE_UserEntity getOneById(@PathVariable String id) {
		return mAFAMILE_UserService.findOneById(id);
	}
	
	
	@PostMapping
	public MAFAMILE_UserEntity saveOne(@RequestBody MAFAMILE_UserEntity mAFAMILE_UserEntity) {
		
		return mAFAMILE_UserService.save(mAFAMILE_UserEntity);
	}
	
	@PutMapping("/{id}")
	public MAFAMILE_UserEntity saveOneById(@PathVariable String id, @RequestBody MAFAMILE_UserEntity mAFAMILE_UserEntity) {
		MAFAMILE_UserEntity newEntity = mAFAMILE_UserService.findOneById(id);
		newEntity = userConvert.Entity_To_Entity(mAFAMILE_UserEntity);
		newEntity.setId(id);
		
		return mAFAMILE_UserService.save(newEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		mAFAMILE_UserService.delete(id);
	}
	
	
	@PutMapping("/role/{id}")
	public MAFAMILE_UserEntity saveOneById(@PathVariable String id, @RequestBody String[] role) {
		MAFAMILE_UserEntity  mAFAMILE_UserEntity = mAFAMILE_UserService.findOneById(id);
		mAFAMILE_UserEntity.setId(id);
		mAFAMILE_UserEntity.setRoles(role);
		
		return mAFAMILE_UserService.save(mAFAMILE_UserEntity);
	}
	
}
