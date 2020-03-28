package com.mojdan.app.model.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

	int count();

    int save(User user);

    int update(User user);

    int deleteById(Long id);

    List<User> findAll();

    List<User> findByName(String name);

    Optional<User> findById(Long id);

    String getNameById(Long id);
    
}
