package com.mafami.Mafami.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "products")
public class ProductEntity {

	@Id
	private String id;
	
	private String idcategory;
	
	private String name;
	private double price;
	
	
	private String image;
	
	 public ProductEntity() {
		// TODO Auto-generated constructor stub
	}


	public String getIdcategory() {
		return idcategory;
	}

	public void setIdcategory(String idcategory) {
		this.idcategory = idcategory;
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
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
	
	
	
}
