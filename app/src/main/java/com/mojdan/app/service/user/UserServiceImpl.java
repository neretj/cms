package com.mojdan.app.service.user;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mojdan.app.model.user.User;
import com.mojdan.app.model.user.UserRepository;
import com.mojdan.app.service.error.UserIdMismatchException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public ArrayList<User> findByName(String name) {
		return new ArrayList<User> (userRepository.findByName(name));
	}

	@Override
	public Optional<User> findOne(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.findById(id);
		userRepository.deleteById(id);
	}

	@Override
	public User updateUser(User user, Long id) {
		if (user.getId() != id) {
			throw new UserIdMismatchException();
		}
		userRepository.findById(id);
		return userRepository.save(user);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}
