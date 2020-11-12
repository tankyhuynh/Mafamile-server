package com.mafami.Mafami.model;

import java.util.UUID;

import com.mafami.Mafami.Entity.AMIA.AMIA_MenuEntity;

/**
* @author root {11:11:28 AM}:
 * @version Creation time: Nov 11, 2020 11:11:28 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

public class AMIA_FoodInformationModel {

	private String id = UUID.randomUUID().toString();
	private AMIA_MenuEntity food;
	private String additionInformation;
	private int quantity;

	public AMIA_FoodInformationModel() {
		// TODO Auto-generated constructor stub
	}

	public AMIA_FoodInformationModel(String id, AMIA_MenuEntity food, int quantity) {
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

	public AMIA_MenuEntity getFood() {
		return food;
	}

	public void setFood(AMIA_MenuEntity food) {
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
