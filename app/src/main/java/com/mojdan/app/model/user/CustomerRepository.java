package com.mojdan.app.model.user;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findAll();

    List<Customer> findByFirstName(String name);

    Optional<Customer> findById(Long id);
    
    Optional<Customer> findByLastName(String lastName);

    String getFirstNameById(Long id);
    
}
