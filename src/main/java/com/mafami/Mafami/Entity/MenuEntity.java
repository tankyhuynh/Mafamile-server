package com.mafami.Mafami.Entity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mafami.Mafami.model.PriceModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "amia_products")
public class MenuEntity {

	@Id
	private String id = (UUID.randomUUID()).toString().substring(0, 8);

	private CategoryEntity category;
	private String name;
	private List<PriceModel> price;
	private String image;
	private String additionInformation;
	private String site;

	

}
