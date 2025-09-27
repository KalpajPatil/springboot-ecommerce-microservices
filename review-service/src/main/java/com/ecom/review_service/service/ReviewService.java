package com.ecom.review_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ecom.review_service.dto.Product;
import com.ecom.review_service.dto.ReviewDto;
import com.ecom.review_service.dto.User;
import com.ecom.review_service.entity.Review;
import com.ecom.review_service.exception.ReviewRuntimeException;
import com.ecom.review_service.feign.ProductServiceClient;
import com.ecom.review_service.feign.UserServiceClient;
import com.ecom.review_service.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {

	@Autowired
	private ProductServiceClient productServiceClient;
	
	@Autowired
	private UserServiceClient userServiceClient;

	@Autowired
	private ReviewRepository reviewRepository;

    @Autowired
    private ProductServiceCircuitBreaker productServiceCircuitBreaker;

	@Transactional
	public Review createReview(ReviewDto reviewDto){
			Product product = productServiceCircuitBreaker.getProductByName(reviewDto.getproductName());
			User user = productServiceCircuitBreaker.getUserById(reviewDto.getReviewerId());
			Review review = new Review();
			review.setReviewTitle(reviewDto.getReviewTitle());
			review.setReviewDescription(reviewDto.getReviewDescription());
			review.setProductId(product.getProductId());
			review.setReviewStar(reviewDto.getReviewStar());
			review.setReviewer(user.getUsername());
			return reviewRepository.save(review);
	}
	
	@Transactional
	public Review updateReview(ReviewDto reviewDto) {
		if(reviewRepository.existsById(reviewDto.getId())) {
			Optional<Review> review = reviewRepository.findById(reviewDto.getId());
			review.get().setReviewTitle(reviewDto.getReviewTitle());
			review.get().setReviewDescription(reviewDto.getReviewDescription());
			review.get().setReviewStar(reviewDto.getReviewStar());
			return reviewRepository.save(review.get());
		}else {
			throw new ReviewRuntimeException("review cannot be found, must have been deleted");
		}
	}
	
	@Transactional
	public boolean deleteReview(long id) {
		try {
			reviewRepository.deleteById(null);
			return true;
		}catch(EmptyResultDataAccessException e) {
			throw new ReviewRuntimeException("review with id " + id + " is already deleted");
		}
	}

	public List<Review> findById(String id) {
		List<Review> reviews = reviewRepository.findAllByProductId(id);
		if(reviews != null && reviews.size() > 0) {
			return reviews;
		}else {
			throw new ReviewRuntimeException("review cannot be found, must have been deleted");
		}
	}
}
