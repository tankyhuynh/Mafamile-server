package com.mafami.Mafami.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hoadon_detail")
public class BillDetailEntity {

	@Id
	private String id;
	
	private ProductEntity id_SanPham;
	private BillEntity id_HoaDon;
	private int quantity;
	private double amount;
	
	public BillDetailEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public BillDetailEntity(ProductEntity id_SanPham, BillEntity id_HoaDon, int quantity, double amount) {
		super();
		this.id_SanPham = id_SanPham;
		this.id_HoaDon = id_HoaDon;
		this.quantity = quantity;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ProductEntity getId_SanPham() {
		return id_SanPham;
	}
	public void setId_SanPham(ProductEntity id_SanPham) {
		this.id_SanPham = id_SanPham;
	}
	public BillEntity getId_HoaDon() {
		return id_HoaDon;
	}
	public void setId_HoaDon(BillEntity id_HoaDon) {
		this.id_HoaDon = id_HoaDon;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
