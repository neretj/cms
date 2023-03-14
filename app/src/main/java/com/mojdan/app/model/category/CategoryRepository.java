package com.mojdan.app.model.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	List<Category> findAll();

	Page<Category> findAll(Pageable pageable);
	
	Optional<Category> findById(Long id);
}
