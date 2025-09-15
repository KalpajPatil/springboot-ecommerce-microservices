package com.ecom.review_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ecom.review_service.dto.User;

@FeignClient(name = "auth-service")
public interface UserServiceClient {

	@GetMapping("/auth/v1/users/{id}")
	User getUserById(@PathVariable("id") long id);
}
