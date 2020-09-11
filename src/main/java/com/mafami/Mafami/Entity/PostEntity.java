package com.mafami.Mafami.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bai_viet")
public class PostEntity {

	@Id
	private String id;
	
	private String content;
	private String title;
	private String shortDescription;
	
	public PostEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public PostEntity(String content, String title, String description) {
		super();	
		this.content = content;
		this.title = title;
		this.shortDescription = description;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	
	
	
	
}
