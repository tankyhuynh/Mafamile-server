package com.mafami.Mafami.Entity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("promotions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionEntity {

	@Id
	private String id = (UUID.randomUUID()).toString().substring(0, 8);
	private String title;
	private String shortDescription;
	private String thumbnail;
	private Date createdDate = Calendar.getInstance().getTime();
	private String url;
	private String site;

}
