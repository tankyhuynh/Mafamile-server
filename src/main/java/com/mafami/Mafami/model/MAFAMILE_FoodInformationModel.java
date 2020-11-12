package com.mafami.Mafami.model;

import java.util.UUID;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_MenuEntity;

/**
* @author root {11:16:38 AM}:
 * @version Creation time: Nov 11, 2020 11:16:38 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
public class MAFAMILE_FoodInformationModel {

	private String id = UUID.randomUUID().toString();
	private MAFAMILE_MenuEntity food;
	private String additionInformation;
	private int quantity;

	public MAFAMILE_FoodInformationModel() {
		// TODO Auto-generated constructor stub
	}

	public MAFAMILE_FoodInformationModel(String id, MAFAMILE_MenuEntity food, int quantity) {
		super();
		this.id = id;
		this.food = food;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MAFAMILE_MenuEntity getFood() {
		return food;
	}

	public void setFood(MAFAMILE_MenuEntity food) {
		this.food = food;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getAdditionInformation() {
		return additionInformation;
	}

	public void setAdditionInformation(String additionInformation) {
		this.additionInformation = additionInformation;
	}

	
	
	
	

}
