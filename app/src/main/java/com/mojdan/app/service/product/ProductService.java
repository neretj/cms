package com.mojdan.app.service.product;

import java.util.Optional;

import com.mojdan.app.model.product.Product;

public interface ProductService {

    public Iterable<Product> getAllProducts();
 
    public Optional<Product> getProduct(long id);
 
    public Product save(Product product);
    
}
