package com.mafami.Mafami.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mafami.Mafami.model.FoodInformationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "combo_foods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboFoodEntity {

	@Id
	private String id;

	private String name;
	private List<String> foods;
	private String thumbnail;
	private double price;
}
