package com.mafami.Mafami.Entity.MAFAMILE;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mafami.Mafami.model.CustomerModel;
import com.mafami.Mafami.model.MAFAMILE_FoodInformationModel;

@Document(collection = "mafamile_bill")
public class MAFAMILE_BillEntity {

	@Id
	private String id;

	private CustomerModel customerInformation;
	private List<MAFAMILE_FoodInformationModel> foodInformation;
	private Date createdDate  = Calendar.getInstance().getTime();
	private Date orderDate;
	private String additionInformation;
	private boolean isConfirmed;
	private double total;
	private String site;

	public MAFAMILE_BillEntity() {
		// TODO Auto-generated constructor stub
	}

	public MAFAMILE_BillEntity(String id, CustomerModel personalInformation, List<MAFAMILE_FoodInformationModel> foodInformation,
			Date createdDate, Date orderDate, String additionInformation, boolean isConfirmed) {
		super();
		this.id = id;
		this.customerInformation = personalInformation;
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
		return customerInformation;
	}

	public void setPersonalInformation(CustomerModel personalInformation) {
		this.customerInformation = personalInformation;
	}

	public List<MAFAMILE_FoodInformationModel> getFoodInformation() {
		return foodInformation;
	}

	public void setFoodInformation(List<MAFAMILE_FoodInformationModel> foodInformation) {
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

	public String getAdditionInformation() {
		return additionInformation;
	}

	public void setAdditionInformation(String additionInformation) {
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	
	
	

}
