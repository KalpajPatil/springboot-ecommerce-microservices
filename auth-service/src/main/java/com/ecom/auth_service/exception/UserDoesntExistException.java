package com.ecom.auth_service.exception;

public class UserDoesntExistException extends RuntimeException{
	public UserDoesntExistException(String message) {
		super(message);
	}
}
