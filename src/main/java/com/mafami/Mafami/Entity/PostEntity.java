package com.mafami.Mafami.Entity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("ami_design_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {

	@Id
	private String id = (UUID.randomUUID()).toString().substring(0, 8);
	private String title;
	private String content;
	private String shortDescription;
	private Date time = Calendar.getInstance().getTime();
	private UserEntity author;
	private String thumbnail;
	private String preview;
	private String site;

}
