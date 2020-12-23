package com.mafami.Mafami.api;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Convert.UserConvert;
import com.mafami.Mafami.Entity.UserEntity;
import com.mafami.Mafami.Service.UserService;
import com.mafami.Mafami.Utils.FileUtils;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserConvert userConvert;
	
	@Autowired
	private FileUtils fileUtils;
	
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
		String password =  userEntity.getPassword();
		String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(12));
		 userEntity.setPassword(hashedPass);
		
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
	public UserEntity saveOneById(@PathVariable String id, @RequestBody String role) {
		UserEntity  userEntity = userService.findOneById(id);
		userEntity.setId(id);
		userEntity.setRole(role);
		
		return userService.save(userEntity);
	}
	
}
