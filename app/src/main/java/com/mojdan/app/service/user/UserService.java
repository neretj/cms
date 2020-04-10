package com.mojdan.app.service.user;

import java.util.ArrayList;
import java.util.Optional;

import com.mojdan.app.model.user.User;

public interface UserService {

	public Iterable<User> findAll();
	
	
	public ArrayList<User> findByName(String name);
	
	
	public Optional<User> findOne(Long id);
	

	public void delete(Long id);
	

	public User updateUser(User user, Long id);
	

	public User save(User user);
	

	public Optional<User> findOne(String userName);
}
