package com.mojdan.app.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.mojdan.app.service.error.UserIdMismatchException;
import com.mojdan.app.service.user.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	/*
	 * @RequestMapping("/user") public Principal user(HttpServletRequest request) {
	 * String authToken = request.getHeader("Authorization")
	 * .substring("Basic".length()).trim(); return () -> new
	 * String(Base64.getDecoder() .decode(authToken)).split(":")[0]; }
	 * 
	 * List<Producto> lista = productoService.obtenerTodos();
        return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	 */

	@GetMapping
	@RequestMapping("/")
	public Iterable<User> findAll() {
		LOGGER.info("Finding all users...");
		Iterable<User> list = userService.findAll();
		LOGGER.info("...returning users ", list.toString());
		return list;
	}

	@GetMapping("/name/{name}")
	public List<User> findByName(@PathVariable String name) {
		LOGGER.trace("Finding user by name...");
		return userService.findByName(name);
	}

	@GetMapping("/find/{id}")
	public User findOne(@PathVariable Long id) {
		LOGGER.info("Finding user by id", id);
		User user = userService.findOne(id).get();
		return user;
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		LOGGER.info("Creating user...", user.toString());
		return userService.create(user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		userService.findOne(id);
		userService.delete(id);
	}

	@PutMapping("/update/{id}")
	public User updateUser(@RequestBody User user, @PathVariable Long id) {
		LOGGER.info("Updating user...", id);
		userService.findOne(id);
		return userService.updateUser(user, id);
	}
}
