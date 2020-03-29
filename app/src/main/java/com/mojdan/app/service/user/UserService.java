package com.mojdan.app.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mojdan.app.model.user.User;

@Service
public interface UserService {

	public Iterable findAll();

	public List findByName(@PathVariable String name);

	public User findOne(@PathVariable Long id);

	public User create(@RequestBody User user);

	public void delete(@PathVariable Long id);

	public User updateUser(@RequestBody User user, @PathVariable Long id);

}
