package com.mojdan.app.service.user;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mojdan.app.model.user.User;
import com.mojdan.app.model.user.UserRepository;
import com.mojdan.app.service.error.UserIdMismatchException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


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
	public void delete(Long id) {
		userRepository.findById(id);
		userRepository.deleteById(id);
	}


	public User updateUser(User user) {
		userRepository.findById(user.getId());
		return userRepository.save(user);
	}

	public User save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User findOne(String userName) {
		Optional<User> user = userRepository.findByUsername(userName);
		return user.get();
	}

	@Override
	public Iterable findAll() {
		return userRepository.findAll();
	}
}
