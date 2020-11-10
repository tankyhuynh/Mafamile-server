package com.mafami.Mafami.Entity.MAFAMILE;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "mafamile_categories")
public class MAFAMILE_CategoryEntity {

	@Id
	private String id;

	private String name;
	private String slug;
	private String site;

	public MAFAMILE_CategoryEntity() {
		// TODO Auto-generated constructor stub
	}

	public MAFAMILE_CategoryEntity(String id, String name, String slug) {
		super();
		this.id = id;
		this.name = name;
		this.slug = slug;
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

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
