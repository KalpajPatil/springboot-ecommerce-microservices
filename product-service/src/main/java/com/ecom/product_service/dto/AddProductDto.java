package com.ecom.product_service.dto;

public class AddProductDto {

	private String productId;

	public String getProductId() {
		return productId;
	}

	private String productName;

	private String productDescription;

	private String productoriginCountry;

	private Long productCategory;

	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProductoriginCountry() {
		return productoriginCountry;
	}

	public Long getProductCategory() {
		return productCategory;
	}

}
