package com.example.dto;

public class ProductCategoryDetailsDto {

	private String id;
	private String productName;
	private double price;
	private String categoryId;
	private String category;
	private double taxInPercent;
	private long quantity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductCategoryDetailsDto [id=" + id + ", productName=" + productName + ", price=" + price
				+ ", categoryId=" + categoryId + ", category=" + category + ", taxInPercent=" + taxInPercent
				+ ", quantity=" + quantity + "]";
	}
	
	
}
