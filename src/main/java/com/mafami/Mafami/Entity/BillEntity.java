package com.mafami.Mafami.Entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "hoadon")
public class BillEntity {

	@Id
	private String id;
	
	private int amount;
	
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date created_day = new Date();
	
	public BillEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public BillEntity(int amount) {
		super();
		this.amount = amount;
	}
	public String getId() {
		return id;
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
	
	
	
}
