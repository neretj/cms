package com.mojdan.app.model.user;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface SellerRepository extends CrudRepository<User, Long> {

	List<User> findAll();

    List<User> findByName(String name);

    Optional<User> findById(Long id);
    
    Optional<User> findByUsername(String username);

    String getNameById(Long id);
    
}
