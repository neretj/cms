package com.mojdan.app.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mojdan.app.model.user.User;
import com.mojdan.app.model.user.UserRepository;
import com.mojdan.app.service.user.util.UserIdMismatchException;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public Iterable findAll() {
		return userRepository.findAll();
	}

	public List findByName(String name) {
		return userRepository.findByName(name);
	}

	public User findOne(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}

	public User create(User user) {
		return userRepository.save(user);
	}

	public void delete(Long id) {
		userRepository.findById(id);
		userRepository.deleteById(id);
	}

	public User updateUser(User user, Long id) {
		if (user.getId() != id) {
			throw new UserIdMismatchException();
		}
		userRepository.findById(id);
		return userRepository.save(user);
	}

}
