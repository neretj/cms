package com.mojdan.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mojdan.app.model.user.User;
import com.mojdan.app.service.user.UserService;
import com.mojdan.app.service.user.util.UserIdMismatchException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String home() {
		return "Hello World";
	}
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	/*
	 * @RequestMapping("/user") public Principal user(HttpServletRequest request) {
	 * String authToken = request.getHeader("Authorization")
	 * .substring("Basic".length()).trim(); return () -> new
	 * String(Base64.getDecoder() .decode(authToken)).split(":")[0]; }
	 */

	@GetMapping
	public Iterable<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/name/{userName}")
	public List<User> findByName(@PathVariable String name) {
		return userService.findByName(name);
	}

	@GetMapping("/{id}")
	@ExceptionHandler()
	public User findOne(@PathVariable Long id) {
		User user = userService.findOne(id);
		return user;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		return userService.create(user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		userService.findOne(id);
		userService.delete(id);
	}

	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable Long id) {
		if (user.getId() != id) {
			throw new UserIdMismatchException();
		}
		userService.findOne(id);
		return userService.save(user);
	}
}
