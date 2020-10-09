package com.mafami.Mafami.api.MAFAMILE;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_UserEntity;
import com.mafami.Mafami.Repository.MAFAMILE.MAFAMILE_UserRepo;
import com.mafami.Mafami.Service.MAFAMILE.MAFAMILE_UserService;
import com.mafami.Mafami.model.MAFAMILE.AuthRequest;
import com.mafami.Mafami.model.MAFAMILE.SigninRequest;

@RestController
public class AuthenticationAPI {

	@Autowired
	private MAFAMILE_UserRepo user_repo;
	
	@Autowired
	private MAFAMILE_UserService MAFAMILE_UserService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String signIn(@RequestBody SigninRequest authenticationRequest)
			throws Exception {
			String token = UUID.randomUUID().toString();
			
			try {
				MAFAMILE_UserEntity user = MAFAMILE_UserService.findByUsernameAndPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword());
				user.setToken(token);
				MAFAMILE_UserService.save(user);
				return token;
				
			} catch (Exception e) {

			}
			return "";
			
		
	}
	
	
	@PostMapping("/auth")
	public boolean auth(@RequestBody AuthRequest authRequest) {
		MAFAMILE_UserEntity user = MAFAMILE_UserService.findByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword());
		if (user != null && authRequest.getToken().equals(user.getToken())) {
			return true;
		}
		return false;
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public boolean signOut(@RequestBody SigninRequest authenticationRequest) {
		try {
			MAFAMILE_UserEntity user = user_repo.findByUsernameAndPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			user.setToken("");
			user_repo.save(user);
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	
	
}
