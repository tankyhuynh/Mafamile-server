package com.mafami.Mafami.Entity.MAFAMILE;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "mafamile_customers")
public class MAFAMILE_CustomerEntity {

	@Id
	private String id;
	private String name;
	private String phone;
	private String email;
	
	public MAFAMILE_CustomerEntity() {
		// TODO Auto-generated constructor stub
	}
	

	public MAFAMILE_CustomerEntity(String id, String name, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}





	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
