package com.ecom.review_service.dto;

public class ReviewDto {

	private long id;

	private String productName;

	private int reviewStar;

	private String reviewTitle;

	private String reviewDescription;
	
	private long reviewerId;

	public long getId() {
		return id;
	}

	public long getReviewerId() {
		return reviewerId;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public int getReviewStar() {
		return reviewStar;
	}

	public String getproductName() {
		return productName;
	}

}
