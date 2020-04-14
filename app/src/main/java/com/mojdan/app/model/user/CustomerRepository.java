package com.mojdan.app.model.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Page<Customer> findAll(Pageable pageable);

	List<Customer> findByFirstName(String name);

	Optional<Customer> findById(Long id);

	Optional<Customer> findByLastName(String lastName);

	String getFirstNameById(Long id);

	@Query(value = "SELECT * FROM Customer c JOIN User e ON c.user_id = e.id WHERE e.username LIKE ?1", nativeQuery = true)
	Optional<Customer> findCustomerByUsername(String username);
}
