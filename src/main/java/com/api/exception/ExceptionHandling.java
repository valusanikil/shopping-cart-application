package com.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.exception.domain.CartNotFoundException;
import com.api.exception.domain.EmailExistsException;
import com.api.exception.domain.ProductNameExistsException;
import com.api.exception.domain.ProductNotFoundException;
import com.api.exception.domain.UserNotFoundException;
import com.api.exception.domain.UsernameExistsException;
import com.api.model.HttpResponse;

@RestControllerAdvice
public class ExceptionHandling {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(EmailExistsException.class)
	public ResponseEntity<HttpResponse> emailExistsException(EmailExistsException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(UsernameExistsException.class)
	public ResponseEntity<HttpResponse> usernameExistsException(UsernameExistsException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<HttpResponse> productNotFoundException(ProductNotFoundException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(ProductNameExistsException.class)
	private ResponseEntity<HttpResponse> productNameExistsException(ProductNameExistsException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(CartNotFoundException.class)
	private ResponseEntity<HttpResponse> cartNotFoundException(CartNotFoundException exception) {
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while processing the request");
    }

	private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(new HttpResponse(httpStatus.value(),httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()),httpStatus);
	}
	
}
