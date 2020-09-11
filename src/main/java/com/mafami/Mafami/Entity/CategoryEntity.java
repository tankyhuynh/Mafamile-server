package com.mafami.Mafami.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
public class CategoryEntity {

	@Id
	private String id;
	
	private String name;
	private String code;
	
	public CategoryEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public CategoryEntity(String name, String code) {
		super();
		this.name = name;
		this.code = code;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}
