package com.ecom.review_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.review_service.dto.Product;

@FeignClient(name = "product-service")
@Component
public interface ProductServiceClient {
	
	 @GetMapping("/product/v1/products/{productName}")
	 Product getProductByName(@PathVariable("productName") String productName);
}
