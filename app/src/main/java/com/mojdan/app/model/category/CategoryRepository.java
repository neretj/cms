package com.mojdan.app.model.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	List<Category> findAll();

	Optional<Category> findById(Long id);
}
