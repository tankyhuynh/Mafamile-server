package com.mafami.Mafami.Entity.AMIA;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "mafamile_posts")
public class AMIA_PostEntity {

	@Id
	private String id;
	private String title;
	private String content;
	private List<String> images;

	private String username;
	
	public AMIA_PostEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public AMIA_PostEntity(String content, String title, String description) {
		super();	
		this.content = content;
		this.title = title;
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
	

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	
	
	
	
}
