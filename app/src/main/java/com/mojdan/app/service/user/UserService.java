package com.mojdan.app.service.user;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mojdan.app.model.user.User;

public interface UserService {

	public Iterable<User> findAll();

	public ArrayList<User> findByName(@PathVariable String name);

	public Optional<User> findOne(@PathVariable Long id);

	public User create(@RequestBody User user);

	public void delete(@PathVariable Long id);

	public User updateUser(@RequestBody User user, @PathVariable Long id);

	public User save(User user);

}
