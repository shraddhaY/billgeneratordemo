package com.example.model;

public class Categories {

	private String id;
	private String category;
	private double taxInPercent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}