package com.ecom.review_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.review_service.dto.ReviewDto;
import com.ecom.review_service.entity.Review;
import com.ecom.review_service.service.ReviewService;

@RestController
@RequestMapping("/review/v1")
public class ReviewController {

	Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/review/create")
	public ResponseEntity<Review> createReview(@RequestBody ReviewDto reviewDto) throws Exception {
		Review review =  reviewService.createReview(reviewDto);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}
	
	@PutMapping("/review/update")
	public ResponseEntity<Review> updateReview(@RequestBody ReviewDto reviewDto){
		Review udpatedReview =  reviewService.updateReview(reviewDto);
		return new ResponseEntity<Review>(udpatedReview, HttpStatus.OK);
	}
	
	@DeleteMapping("/review/delete/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable("id") long id){
		if(reviewService.deleteReview(id)) {
			return new ResponseEntity<String>(new String("review deleted"), HttpStatus.OK);
		}
		return new ResponseEntity<String>(new String("error during deletion"), HttpStatus.OK);
	}
	
	@GetMapping("/review/{productId}")
	public ResponseEntity<List<Review>> getReviewsById(@PathVariable("productId") String productId){
		List<Review> reviews = reviewService.findById(productId);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}
}
