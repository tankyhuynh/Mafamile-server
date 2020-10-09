package com.mafami.Mafami.Entity.MAFAMILE;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "mafamile_bills")
public class BillEntity {

	@Id
	private String id;

	private String productID;

	private String customerID;

	private int amount;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date created_day = new Date();

	private double total_price;

	public BillEntity() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getCreated_day() {
		return created_day;
	}

	public void setCreated_day(Date created_day) {
		this.created_day = created_day;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

}
