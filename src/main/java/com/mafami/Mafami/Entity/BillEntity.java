package com.mafami.Mafami.Entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "bills")
public class BillEntity {

	@Id
	private String id;
	
	private String idproduct;
	

	private String idcustomer;
	
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


	


	public String getIdproduct() {
		return idproduct;
	}



	public void setIdproduct(String idproduct) {
		this.idproduct = idproduct;
	}



	public String getIdcustomer() {
		return idcustomer;
	}



	public void setIdcustomer(String idcustomer) {
		this.idcustomer = idcustomer;
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
