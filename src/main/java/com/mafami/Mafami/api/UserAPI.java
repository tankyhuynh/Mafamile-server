package com.mafami.Mafami.api;

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

import com.mafami.Mafami.Convert.UserConvert;
import com.mafami.Mafami.Entity.UserEntity;
import com.mafami.Mafami.Service.User_Service;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

	@Autowired
	private User_Service user_Service;
	
	@Autowired
	private UserConvert userConvert;
	
	@GetMapping
	public List<UserEntity> getAll() {
		return user_Service.getAll();
	}

	@GetMapping("/{id}")
	public UserEntity getUserInfo(@PathVariable String id) {
		return user_Service.findOneById(id);
	}
	
	@PostMapping
	public UserEntity addNewUser(@RequestBody UserEntity userEntity) {
		
		return user_Service.save(userEntity);
	}
	
	@PutMapping("/{id}")
	public UserEntity updateUser(@PathVariable String id, @RequestBody UserEntity userEntity) {
		UserEntity newEntity = user_Service.findOneById(id);
		newEntity = userConvert.Entity_To_Entity(userEntity);
		newEntity.setId(id);
		
		return user_Service.save(newEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id) {
		user_Service.delete(id);
	}
	
	
	@PutMapping("/role/{id}")
	public UserEntity updateRole(@PathVariable String id, @RequestBody String[] role) {
		UserEntity  userEntity = user_Service.findOneById(id);
		userEntity.setId(id);
		userEntity.setRole(role);
		
		return user_Service.save(userEntity);
	}
	
}
