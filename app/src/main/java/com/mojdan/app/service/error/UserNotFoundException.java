package com.mojdan.app.service.error;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
