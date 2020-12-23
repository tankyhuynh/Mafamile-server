package com.mafami.Mafami.api;

import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.UserEntity;
import com.mafami.Mafami.Repository.UserRepo;
import com.mafami.Mafami.Service.UserService;
import com.mafami.Mafami.model.SigninRequest;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthenticationAPI {

	@Autowired
	private UserRepo user_repo;

	@Autowired
	private UserService userService;
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserEntity> signIn(@RequestBody SigninRequest authenticationRequest) throws Exception {
		String token = UUID.randomUUID().toString();
		UserEntity user = new UserEntity();
		boolean check2;
		
		String password = authenticationRequest.getPassword();
		String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
		
		try {
			user = userService.findByUsername(authenticationRequest.getUsername());
			check2 = true;
		} catch (Exception e) {
			check2 = false;
		}
		
		String passwordCheck = user.getPassword();
		boolean check = BCrypt.checkpw(password, passwordCheck);
		
		if ( check && check2 ) {
			return ResponseEntity.ok(user);
		} else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

	}

	@PostMapping("/auth")
	public ResponseEntity<String> auth(@RequestBody UserEntity authRequest) {

		return null;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<String> signOut(@RequestBody UserEntity userRequest) {
		try {
			UserEntity user = user_repo.findByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword());
			user_repo.save(user);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

	}

}
