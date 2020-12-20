package com.mafami.Mafami.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "amia_categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {

	@Id
	private String id;
	private String name;
	private String slug;
	private String site;


}
