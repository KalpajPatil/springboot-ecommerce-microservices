package com.ecom.review_service.service;

import com.ecom.review_service.dto.Product;
import com.ecom.review_service.dto.User;
import com.ecom.review_service.exception.ReviewRuntimeException;
import com.ecom.review_service.feign.ProductServiceClient;
import com.ecom.review_service.feign.UserServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceCircuitBreaker {

    private final ProductServiceClient productServiceClient;
    private final UserServiceClient userServiceClient;

    public ProductServiceCircuitBreaker(ProductServiceClient productServiceClient, UserServiceClient userServiceClient){
        this.productServiceClient = productServiceClient;
        this.userServiceClient = userServiceClient;
    }

    @CircuitBreaker(name = "review-service", fallbackMethod = "productServiceOffline")
    public Product getProductByName(String productName) {
        return productServiceClient.getProductByName(productName);
    }

    public Product productServiceOffline(String productName, Throwable ex) throws ReviewRuntimeException {
        throw new ReviewRuntimeException("product service is offline..please wait");
    }

    @CircuitBreaker(name = "review-service", fallbackMethod = "userServiceOffline")
    public User getUserById(long reviewerId) {
        return userServiceClient.getUserById(reviewerId);
    }

    public User userServiceOffline(long reviewerId, Throwable ex) throws ReviewRuntimeException {
        throw new ReviewRuntimeException("user service is offline..please wait");
    }
}
