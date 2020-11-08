package com.mafami.Mafami.Entity.AMI_DESIGN;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mafami.Mafami.Entity.UserEntity;

@Document("ami_design_post")
public class AMIDESIGN_PostEntity {

	@Id
	private String id;
	private String title;
	private String content;
	private Date time = Calendar.getInstance().getTime();
	private UserEntity author;
	private String thumbnail;
	private String preview;
	private String site;

	public AMIDESIGN_PostEntity() {
		// TODO Auto-generated constructor stub
	}

	public AMIDESIGN_PostEntity(String id, String title, String content, Date time, UserEntity author, String site) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
		this.author = author;
		this.site = site;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public UserEntity getAuthor() {
		return author;
	}

	public void setAuthor(UserEntity author) {
		this.author = author;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

}
