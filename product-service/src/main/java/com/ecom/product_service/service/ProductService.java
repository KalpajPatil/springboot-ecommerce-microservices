package com.ecom.product_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecom.product_service.dto.AddProductDto;
import com.ecom.product_service.dto.ReviewDto;
import com.ecom.product_service.entity.Product;
import com.ecom.product_service.exception.NoProductsFoundException;
import com.ecom.product_service.feign.ReviewServiceClient;
import com.ecom.product_service.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductCategoryService productCategoryService;
	private final CacheManager cacheManager;
	private final ReviewServiceClient reviewServiceClient;

	ProductService(ProductRepository productRepository, ProductCategoryService productCategoryService,
			CacheManager cacheManager, ReviewServiceClient reviewServiceClient) {
		this.productRepository = productRepository;
		this.productCategoryService = productCategoryService;
		this.cacheManager = cacheManager;
		this.reviewServiceClient = reviewServiceClient;
	}

	@Cacheable(value = "products-all", key = "{#pageable.pageNumber, #pageable.pageSize, #pageable.sort}")
	public List<Product> getAllProducts(Pageable pageable) {
		if (productRepository.findAll().isEmpty()) {
			throw new NoProductsFoundException("no products found");
		} else {
			return productRepository.findAll(pageable).getContent();
		}
	}

	@Cacheable(value = "product", key = "#productName")
	public Product getProductByProductName(String productName) {
		Optional<Product> product = productRepository.findProductByProductName(productName);
		if (product.isPresent()) {
			return product.get();
		} else {
			throw new NoProductsFoundException("no products found by the name " + productName);
		}
	}

	public Product addProduct(AddProductDto addProductDto) {
		if (productCategoryService.isProductCategoryExists(addProductDto.getProductCategory())) {
			Product product = new Product();
			product.setProductId(addProductDto.getProductId());
			product.setProductName(addProductDto.getProductName());
			product.setProductDescription(addProductDto.getProductDescription());
			product.setProductoriginCountry(addProductDto.getProductoriginCountry());
			product.setProductCategory(
					productCategoryService.getCategoryById(addProductDto.getProductCategory()).get());
			return productRepository.save(product);
		} else {
			throw new NoProductsFoundException("product should belong to a valid cateogry");
		}
	}

	public boolean deleteById(String id) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NoProductsFoundException("product not found with id " + id));
		String productName = product.getProductName();
		productRepository.deleteById(id);
		// Manual eviction AFTER successful deletion
		cacheManager.getCache("product").evict(productName);
		cacheManager.getCache("products-all").clear();
		return true;
	}
	
	public List<ReviewDto> getReviewsByProductId(String id){
		List<ReviewDto> reviews = reviewServiceClient.getReviewsByProductId(id);
		return reviews;
	}
}
