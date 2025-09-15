package com.ecom.product_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecom.product_service.dto.ErrorResponse;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoProductsFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoProductsFoundException(NoProductsFoundException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
