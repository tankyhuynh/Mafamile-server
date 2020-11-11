package com.mafami.Mafami.Entity.AMIA;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mafami.Mafami.model.AMIA_FoodInformationModel;
import com.mafami.Mafami.model.CustomerModel;

/**
* @author root {10:45:58 AM}:
 * @version Creation time: Nov 11, 2020 10:45:58 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Document(collection = "amia_bill")
public class AMIA_BillEntity {

	@Id
	private String id;
	
	private CustomerModel personalInformation;
	private List<AMIA_FoodInformationModel> foodInformation;
	private Date createdDate  = Calendar.getInstance().getTime();
	private Date orderDate;
	private List<String> additionInformation;
	private boolean isConfirmed;
	private String site;
	
	public AMIA_BillEntity() {
		// TODO Auto-generated constructor stub
	}

	public AMIA_BillEntity(String id, CustomerModel personalInformation, List<AMIA_FoodInformationModel> foodInformation,
			Date createdDate, Date orderDate, List<String> additionInformation, boolean isConfirmed) {
		super();
		this.id = id;
		this.personalInformation = personalInformation;
		this.foodInformation = foodInformation;
		this.createdDate = createdDate;
		this.orderDate = orderDate;
		this.additionInformation = additionInformation;
		this.isConfirmed = isConfirmed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustomerModel getPersonalInformation() {
		return personalInformation;
	}

	public void setPersonalInformation(CustomerModel personalInformation) {
		this.personalInformation = personalInformation;
	}

	public List<AMIA_FoodInformationModel> getFoodInformation() {
		return foodInformation;
	}

	public void setFoodInformation(List<AMIA_FoodInformationModel> foodInformation) {
		this.foodInformation = foodInformation;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<String> getAdditionInformation() {
		return additionInformation;
	}

	public void setAdditionInformation(List<String> additionInformation) {
		this.additionInformation = additionInformation;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
