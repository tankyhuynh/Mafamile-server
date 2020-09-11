package com.mafami.Mafami.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sanpham")
public class ProductEntity {

	@Id
	private String id;
	
	private String name;
	private String code;
	private double price;
	private String id_Category;
	
	 public ProductEntity() {
		// TODO Auto-generated constructor stub
	}

	public ProductEntity(String name, String code, double price, String id_Category) {
		super();
		this.name = name;
		this.code = code;
		this.price = price;
		this.id_Category = id_Category;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getId_Category() {
		return id_Category;
	}

	public void setId_Category(String id_Category) {
		this.id_Category = id_Category;
	}
	
	
	
	
	
	
}
