package com.ecom.product_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.product_service.dto.ReviewDto;

@FeignClient("review-service")
public interface ReviewServiceClient {
	
	@GetMapping("/review/v1/review/{productId}")
	public List<ReviewDto> getReviewsByProductId(@PathVariable("productId") String productId);
}
