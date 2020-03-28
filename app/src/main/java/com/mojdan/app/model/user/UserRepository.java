package com.mojdan.app.model.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    List<User> findByName(String name);

    Optional<User> findById(Long id);

    String getNameById(Long id);
    
}
