package com.ecom.product_service.service;

import com.ecom.product_service.dto.ReviewDto;
import com.ecom.product_service.exception.ReviewServiceOfflineException;
import com.ecom.product_service.feign.ReviewServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ReviewServiceCircuitBreaker{
    ReviewServiceClient reviewServiceClient;

    public ReviewServiceCircuitBreaker(ReviewServiceClient reviewServiceClient){
        this.reviewServiceClient = reviewServiceClient;
    }

    @CircuitBreaker(name = "product-service", fallbackMethod = "getOfflineReviews")
    public List<ReviewDto> getReviewsByProductId(String id){
        List<ReviewDto> reviews = reviewServiceClient.getReviewsByProductId(id);
        return reviews;
    }


    public List<ReviewDto> getOfflineReviews(String productId, Throwable throwable) throws ReviewServiceOfflineException{
        throw new ReviewServiceOfflineException("review service is down..wait for some time");
    }
}
