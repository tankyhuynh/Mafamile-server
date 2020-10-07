package com.mafami.Mafami.Entity.AMI_DESIGN;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ami_design_post")
public class GalleryEntity {

	@Id
	private String id;
	private String title;
	private String content;
	private List<String> image;
	private String username;
	
	
	public GalleryEntity() {
		// TODO Auto-generated constructor stub
	}
	
	


	public GalleryEntity(String id, String title, String content, List<String> image, String username) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
		this.username = username;
	}




	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public List<String> getImage() {
		return image;
	}


	public void setImage(List<String> image) {
		this.image = image;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
