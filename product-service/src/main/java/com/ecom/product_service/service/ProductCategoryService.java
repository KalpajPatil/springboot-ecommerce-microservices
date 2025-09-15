package com.ecom.product_service.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.product_service.dto.AddCategoryDto;
import com.ecom.product_service.entity.ProductCategory;
import com.ecom.product_service.exception.NoProductsFoundException;
import com.ecom.product_service.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	private ProductCategoryRepository productCategoryRepository;

	ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
		this.productCategoryRepository = productCategoryRepository;
	}

	public boolean isProductCategoryExists(long id) {
		return productCategoryRepository.existsById(id);
	}

	public Optional<ProductCategory> getCategoryById(long id) {
		return productCategoryRepository.findById(id);
	}

	public ProductCategory addCategory(AddCategoryDto addCategoryDto) {
		ProductCategory pc = new ProductCategory();
		pc.setCategoryName(addCategoryDto.getCategoryName());
		if(productCategoryRepository.existsByCategoryName(addCategoryDto.getCategoryName())) {
			throw new NoProductsFoundException("product category already exists");
		}
		return productCategoryRepository.save(pc);
	}
}
