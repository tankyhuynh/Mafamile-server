package com.mafami.Mafami.Entity.AMIA;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "amia_products")
public class AMIA_MenuEntity {

	@Id
	private String id;

	private AMIA_CategoryEntity category;
	private String name;
	private double price;
	private String image;
	private String additionInformation;
	private String site;

	public AMIA_MenuEntity() {
		// TODO Auto-generated constructor stub
	}

	public AMIA_MenuEntity(String id, AMIA_CategoryEntity categoryCode, String name, double price, String image) {
		super();
		this.id = id;
		this.category = categoryCode;
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

	public AMIA_CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(AMIA_CategoryEntity category) {
		this.category = category;
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

	public String getAdditionInformation() {
		return additionInformation;
	}

	public void setAdditionInformation(String additionInformation) {
		this.additionInformation = additionInformation;
	}

}
