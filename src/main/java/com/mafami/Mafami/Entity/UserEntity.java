package com.mafami.Mafami.Entity;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class UserEntity {

	@Id
	private String id;
	private String userName;
	private String password;
	private String fullName;
	private String[] role;
	private String token;
	
	public UserEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public UserEntity(String userName, String password, String fullName, String[] role) {
		super();
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.role = role;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String[] getRole() {
		return role;
	}
	public void setRole(String[] role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
	
	
	
	
	
	

	
}
