package com.mafami.Mafami.Entity.MAFAMILE;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user")
public class UserEntity {

	@Id
	private String id;

	private String username;
	
	private String password;
	
	private String fullname;
	
	private Object[] roles;
	
	private String token;
	
	public UserEntity() {
		// TODO Auto-generated constructor stub
	}
	

	


	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}










	public String getFullname() {
		return fullname;
	}





	public void setFullname(String fullname) {
		this.fullname = fullname;
	}





	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Object[] getRoles() {
		return roles;
	}

	public void setRoles(Object[] roles) {
		this.roles = roles;
	}

	
	
	
	
	
	
	
	
	
	
	

	
}
