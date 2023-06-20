package com.api.exception.domain;

@SuppressWarnings("serial")
public class ProductNameExistsException extends Exception {
	
	public ProductNameExistsException(String message) {
		super(message);
	}

}
