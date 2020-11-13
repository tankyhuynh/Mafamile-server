package com.mafami.Mafami.Entity.AMIA;

import java.util.Map;

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
public class AMIA_MenuEntity {

	@Id
	private String id;

	private AMIA_CategoryEntity category;
	private String name;
	private PriceModel price;
	private String image;
	private String additionInformation;
	private String site;

	

}
