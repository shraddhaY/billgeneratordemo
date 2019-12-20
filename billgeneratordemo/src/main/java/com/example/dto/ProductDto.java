package com.example.dto;

public class ProductDto {

	private String productId;
	private long quantity;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductDto [productId=" + productId + ", quantity=" + quantity + "]";
	}
	
	
}
