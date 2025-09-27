package com.ecom.product_service.feign;

import java.util.List;

import com.ecom.product_service.exception.ReviewServiceOfflineException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.product_service.dto.ReviewDto;

@FeignClient("review-service")
@Component
public interface ReviewServiceClient {
	
	@GetMapping("/review/v1/review/{productId}")
	public List<ReviewDto> getReviewsByProductId(@PathVariable("productId") String productId);

    //public List<ReviewDto> getOfflineReviews(@PathVariable("productId") String productId, Throwable throwable) throws ReviewServiceOfflineException;
}
