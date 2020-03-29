package com.mojdan.app.service.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.mojdan.app.model.product.Product;
import com.mojdan.app.model.product.ProductRepository;

public class ProductServiceImpl implements ProductService {
	 
	@Autowired
	private ProductRepository productRepository;
	
    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
 
    @Override
    public Optional<Product> getProduct(long id) {
        return productRepository
          .findById(id);
    }
 
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    
}
