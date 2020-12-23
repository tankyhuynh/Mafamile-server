package com.mafami.Mafami.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("promotion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionEntity {

	@Id
	private String id;
	private String title;
	private String shortDescription;
	private String thumbnail;
	private String site;
	
	
	
}
