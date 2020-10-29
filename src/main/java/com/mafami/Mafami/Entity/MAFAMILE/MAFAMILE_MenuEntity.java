package com.mafami.Mafami.Entity.MAFAMILE;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "mafamile_products")
public class MAFAMILE_MenuEntity {

	@Id
	private String id;

	private String categoryCode;

	private String name;
	private double price;

	private String image;
	private String site;
	

	public MAFAMILE_MenuEntity() {
		// TODO Auto-generated constructor stub
	}

	public MAFAMILE_MenuEntity(String id, String categoryCode, String name, double price, String image) {
		super();
		this.id = id;
		this.categoryCode = categoryCode;
		this.name = name;
		this.price = price;
		this.image = image;
	}
	
	

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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
