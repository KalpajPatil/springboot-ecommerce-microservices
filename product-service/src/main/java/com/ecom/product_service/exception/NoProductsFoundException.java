package com.ecom.product_service.exception;

public class NoProductsFoundException extends RuntimeException{
	public NoProductsFoundException(String message) {
		super(message);
	}
}
