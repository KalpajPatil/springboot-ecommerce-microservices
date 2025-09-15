package com.ecom.product_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.product_service.dto.AddCategoryDto;
import com.ecom.product_service.entity.ProductCategory;
import com.ecom.product_service.service.ProductCategoryService;

@Controller
@RequestMapping("/product/v1")
public class ProductCategoryController {
	
	@Autowired
	ProductCategoryService productCategoryService;
	
	@PostMapping("/category/add")
	public ResponseEntity<ProductCategory> addNewCategory(@RequestBody AddCategoryDto addCategoryDto) {
		ProductCategory pc = productCategoryService.addCategory(addCategoryDto);
		return new ResponseEntity<>(pc, HttpStatus.OK);
	}
}
