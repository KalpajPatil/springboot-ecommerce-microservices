package com.ecom.review_service.dto;

public class Product {
	private String productId;

	private String productName;

	private String productDescription;

	private String productoriginCountry;
	
	private long productCategoryId;
	
	private ProductCategory productCategory;

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProductoriginCountry() {
		return productoriginCountry;
	}

	public long getProductCategoryId() {
		return productCategoryId;
	}
	
	
}
