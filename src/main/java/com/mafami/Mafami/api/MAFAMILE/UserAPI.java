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
import com.mafami.Mafami.Entity.MAFAMILE.UserEntity;
import com.mafami.Mafami.Service.MAFAMILE.UserService;

@RestController
@RequestMapping("/api/mafamile/user")
public class UserAPI {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserConvert userConvert;
	
	@GetMapping
	public List<UserEntity> getAll() {
		return userService.getAll();
	}

	@GetMapping("/{id}")
	public UserEntity getOneById(@PathVariable String id) {
		return userService.findOneById(id);
	}
	
	
	@PostMapping
	public UserEntity saveOne(@RequestBody UserEntity userEntity) {
		
		return userService.save(userEntity);
	}
	
	@PutMapping("/{id}")
	public UserEntity saveOneById(@PathVariable String id, @RequestBody UserEntity userEntity) {
		UserEntity newEntity = userService.findOneById(id);
		newEntity = userConvert.Entity_To_Entity(userEntity);
		newEntity.setId(id);
		
		return userService.save(newEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		userService.delete(id);
	}
	
	
	@PutMapping("/role/{id}")
	public UserEntity saveOneById(@PathVariable String id, @RequestBody String[] role) {
		UserEntity  userEntity = userService.findOneById(id);
		userEntity.setId(id);
		userEntity.setRoles(role);
		
		return userService.save(userEntity);
	}
	
}
