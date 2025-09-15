package com.ecom.review_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecom.review_service.dto.ErrorResponse;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ReviewRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleNoReviewsFoundException(ReviewRuntimeException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
