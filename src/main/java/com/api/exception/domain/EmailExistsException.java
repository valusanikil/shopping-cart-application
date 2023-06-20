package com.api.exception.domain;

@SuppressWarnings("serial")
public class EmailExistsException extends Exception {

	public EmailExistsException(String message) {
		super(message);
	}
}
