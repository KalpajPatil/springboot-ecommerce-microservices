package com.ecom.auth_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecom.auth_service.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(RoleAlreadyExistsException.class)
//    public ResponseEntity<ErrorResponse> handleRoleAlreadyExists(RoleAlreadyExistsException ex) {
//        ErrorResponse error = new ErrorResponse(
//            HttpStatus.CONFLICT.value(),
//            ex.getMessage(),
//            LocalDateTime.now()
//        );
//        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
//    }

	@ExceptionHandler(IncorrectPassword.class)
	public ResponseEntity<ErrorResponse> handleIncorrectPasswordException(IncorrectPassword ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

	@ExceptionHandler(UserDoesntExistException.class)
	public ResponseEntity<ErrorResponse> userDoesntExist(UserDoesntExistException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
