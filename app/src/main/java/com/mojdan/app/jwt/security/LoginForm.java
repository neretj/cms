package com.mojdan.app.jwt.security;

public class LoginForm {

	private String username;
	private String password;
	
	public LoginForm() {

	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
