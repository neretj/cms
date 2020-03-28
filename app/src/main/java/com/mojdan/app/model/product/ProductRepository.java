package com.mojdan.app.model.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>{

    List<Product> findAll();

    List<Product> findByNameAndPrice(String name, BigDecimal price);

    Optional<Product> findById(Long id);

    String getNameById(Long id);

    
}
