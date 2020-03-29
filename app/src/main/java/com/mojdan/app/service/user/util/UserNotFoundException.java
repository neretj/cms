package com.mojdan.app.service.user.util;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
