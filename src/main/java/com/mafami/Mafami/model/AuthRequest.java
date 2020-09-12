package com.mafami.Mafami.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class AuthRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private String userName;
	private String password;
	private String token;

	//default constructor for JSON Parsing
	public AuthRequest()
	{
	}

	public AuthRequest(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	public String getUsername() {
		return this.userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return this.password;
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

	
	
	
	
}
