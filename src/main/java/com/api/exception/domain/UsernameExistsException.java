package com.api.exception.domain;


@SuppressWarnings("serial")
public class UsernameExistsException extends Exception {

	public UsernameExistsException(String message) {
		super(message);
	}
}
