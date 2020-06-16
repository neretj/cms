package com.mojdan.app.model.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findAll();

	Page<Product> findAll(Pageable pageRequest);

	List<Product> findByName(String name);

	List<Product> findByNameAndPrice(String name, BigDecimal price);

	Optional<Product> findById(Long id);

	List<Product> findByIsActiveTrue();

	List<Product> findByIsActiveTrueAndCategory(int categoryId);

	// List<Product> findPopularActiveProducts(int count);

}
