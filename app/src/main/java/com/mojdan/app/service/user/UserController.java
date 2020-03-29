package com.mojdan.app.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public Iterable findAll() {
		return userService.findAll();
	}

	@GetMapping("/name/{userName}")
	public List findByName(@PathVariable String name) {
		return userService.findByName(name);
	}

	/*@GetMapping("/{id}")
	@ExceptionHandler()
	public User findOne(@PathVariable Long id) {
		Optional<User> user = userService.findById(id);
		return user.get();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		return userService.save(user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		userService.findById(id);
		userService.deleteById(id);
	}

	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable Long id) {
		if (user.getId() != id) {
			throw new UserIdMismatchException();
		}
		userService.findById(id);
		return userService.save(user);
	}*/
}
