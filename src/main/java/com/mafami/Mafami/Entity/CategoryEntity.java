package com.mafami.Mafami.Entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {

	@Id
	private String id = (UUID.randomUUID()).toString().substring(0, 8);
	private String name;
	private String slug;
	private String site;


}
