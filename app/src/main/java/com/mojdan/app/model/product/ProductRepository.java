package com.mojdan.app.model.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {


    int count();

    int save(Product product);

    int update(Product product);

    int deleteById(Long id);

    List<Product> findAll();

    List<Product> findByNameAndPrice(String name, BigDecimal price);

    Optional<Product> findById(Long id);

    String getNameById(Long id);

    
}
