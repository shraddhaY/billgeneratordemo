package com.example.dto;

public class BillDetailsDto {

	private String id;
	private String billMasterId;
	private String productMasterId;
	private String productName;
	private long quantity;
	private double perProductPrice;
	private double price;
	private double taxableAmount;
	private double totalAmount;
	private String categoryId;
	private String category;
	private double taxInPercent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBillMasterId() {
		return billMasterId;
	}

	public void setBillMasterId(String billMasterId) {
		this.billMasterId = billMasterId;
	}

	public String getProductMasterId() {
		return productMasterId;
	}

	public void setProductMasterId(String productMasterId) {
		this.productMasterId = productMasterId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getPerProductPrice() {
		return perProductPrice;
	}

	public void setPerProductPrice(double perProductPrice) {
		this.perProductPrice = perProductPrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getTaxInPercent() {
		return taxInPercent;
	}

	public void setTaxInPercent(double taxInPercent) {
		this.taxInPercent = taxInPercent;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
