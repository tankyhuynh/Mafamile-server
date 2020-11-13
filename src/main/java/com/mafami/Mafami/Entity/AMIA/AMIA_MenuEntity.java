package com.mafami.Mafami.Entity.AMIA;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "amia_products")
public class AMIA_MenuEntity {

	@Id
	private String id;

	private AMIA_CategoryEntity category;
	private String name;
	private Object price;
	private String image;
	private String additionInformation;
	private String site;

	

}
