package com.mafami.Mafami.Entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mafami.Mafami.model.FoodInformationModel;
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

@Document(collection = "bills")
public class BillEntity {

	@Id
	private String id;

	private CustomerModel customerInformation;
	private List<FoodInformationModel> foodInformation;
	private Date createdDate = Calendar.getInstance().getTime();
	private Date orderDate;
	private String additionInformation;
	private boolean isConfirmed;
	private double total;
	private String site;

	public BillEntity() {
		// TODO Auto-generated constructor stub
	}

	public BillEntity(String id, CustomerModel customerInformation,
			List<FoodInformationModel> foodInformation, Date createdDate, Date orderDate,
			String additionInformation, boolean isConfirmed) {
		super();
		this.id = id;
		this.customerInformation = customerInformation;
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

	

	public CustomerModel getCustomerInformation() {
		return customerInformation;
	}

	public void setCustomerInformation(CustomerModel customerInformation) {
		this.customerInformation = customerInformation;
	}

	public List<FoodInformationModel> getFoodInformation() {
		return foodInformation;
	}

	public void setFoodInformation(List<FoodInformationModel> foodInformation) {
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
