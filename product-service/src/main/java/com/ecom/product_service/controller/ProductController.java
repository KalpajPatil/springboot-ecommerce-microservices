package com.ecom.product_service.controller;

import java.util.ArrayList;
import java.util.List;

import com.ecom.product_service.service.ReviewServiceCircuitBreaker;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.product_service.dto.AddProductDto;
import com.ecom.product_service.dto.ReviewDto;
import com.ecom.product_service.entity.Product;
import com.ecom.product_service.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/product/v1")
@Tag(name = "Product", description = "API for managing Products")
public class ProductController {

	private ProductService productService;
    private ReviewServiceCircuitBreaker reviewServiceCircuitBreaker;
	public ProductController(ProductService productService, ReviewServiceCircuitBreaker reviewServiceCircuitBreaker) {
		this.productService = productService;
        this.reviewServiceCircuitBreaker = reviewServiceCircuitBreaker;
	}

	//implements pagination, sorting and filtering
	@GetMapping("/products")
	@Operation(summary = "Get all products")
	public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false, defaultValue = "1") int pageNo,
			@RequestParam(required = false, defaultValue = "5") int pageSize, @RequestParam(required = false, defaultValue = "ASC") String sortDir,
			@RequestParam(required = false, defaultValue = "productName") String sortBy, @RequestParam(required = false) String productName) {
		List<Product> productList = new ArrayList<Product>();
		if(productName != null) {
			productList.add(productService.getProductByProductName(productName));	
		} else {
			Sort sort = null;
			if(sortDir.equalsIgnoreCase("ASC")) {
				sort = Sort.by(sortBy).ascending();
			}
			else {
				sort = Sort.by(sortBy).descending();
			}
			productList = productService.getAllProducts(PageRequest.of(pageNo, pageSize, sort));
		}
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@GetMapping("/products/{productName}")
	@Operation(summary = "Get products by name")
	public ResponseEntity<Product> getProductByProductName(@PathVariable String productName) {
		Product product = productService.getProductByProductName(productName);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping("/product/add")
	@Operation(summary = "Add new products")
	public ResponseEntity<Product> addNewProduct(@RequestBody AddProductDto addProductDto) {
		Product product = productService.addProduct(addProductDto);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable("id") String id){
		if(productService.deleteById(id)) {
			return new ResponseEntity<String>(new String("product with id }" + id + "deleted"), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(new String("failed to delete"), HttpStatus.OK);
		}
	}
	
	@GetMapping("/reviews/{productId}")
	public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable("productId") String productId){
		List<ReviewDto> reviews = reviewServiceCircuitBreaker.getReviewsByProductId(productId);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

}
