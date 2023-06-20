package com.api.exception.domain;

@SuppressWarnings("serial")
public class CartNotFoundException extends Exception {
	
	public CartNotFoundException(String message) {
		super(message);
	}

}
