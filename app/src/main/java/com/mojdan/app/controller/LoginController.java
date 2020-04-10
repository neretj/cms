package com.mojdan.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mojdan.app.model.user.User;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class LoginController {
	
	@RequestMapping("/login")
	public boolean login(@RequestBody User user) {
		return user.getUsername().equals("user") && user.getPassword().equals("password");
	}
	
}
