package com.mafami.Mafami.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.UserEntity;
import com.mafami.Mafami.Repository.User_Repo;
import com.mafami.Mafami.Service.User_Service;
import com.mafami.Mafami.model.AuthRequest;

@RestController
public class AuthenticationAPI {

	@Autowired
	private User_Repo user_repo;
	
	@Autowired
	private User_Service User_Service;

	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public String signIn(@RequestBody AuthRequest authenticationRequest)
			throws Exception {
			String token = UUID.randomUUID().toString();
			
			try {
				UserEntity user = User_Service.findByUserNameAndPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword());
				user.setToken(token);
				User_Service.save(user);
				return token;
				
			} catch (Exception e) {

			}
			return "";
			
		
	}
	
	
	@PostMapping("/auth")
	public boolean auth(@RequestBody AuthRequest authRequest) {
		UserEntity user = User_Service.findByUserNameAndPassword(authRequest.getUsername(), authRequest.getPassword());
		if (user != null && authRequest.getToken().equals(user.getToken())) {
			return true;
		}
		return false;
	}
	
	
	@RequestMapping(value = "/signOut", method = RequestMethod.POST)
	public String logOut(@RequestBody AuthRequest authenticationRequest) {
		UserEntity user = user_repo.findByUserNameAndPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		user.setToken("");
		user_repo.save(user);
		 return "ok";
	}
	
	
	
}
