package com.ecom.product_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.product_service.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	Optional<Product> findProductByProductName(String productName);
}
