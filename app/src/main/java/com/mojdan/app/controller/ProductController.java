package com.mojdan.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mojdan.app.service.product.ProductService;
import com.mojdan.app.service.product.dto.ProductDTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/products")
public class ProductController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public Iterable<ProductDTO> findAllProducts(@RequestParam("page") int page, @RequestParam("size") int size) {
		Pageable pageable = PageRequest.of(page, size);
		LOGGER.info("Finding all users...");
		Iterable<ProductDTO> list = productService.getAllProducts(pageable);
		LOGGER.info("...returning users ", list.toString());
		return list;
	}

	@GetMapping("/find/id")
	public ProductDTO findOne(@RequestParam(value = "id") Long id) {
		LOGGER.info("Finding product by id", id);
		ProductDTO product = productService.findOne(id);
		return product;
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO create(@RequestBody ProductDTO product) {
		LOGGER.info("Creating product...", product.toString());
		return productService.save(product);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		productService.findOne(id);
		productService.delete(id);
	}

	@PutMapping("/update/{id}")
	public ProductDTO updateUser(@RequestBody ProductDTO product, @PathVariable Long id) {
		LOGGER.info("Updating product...", id);
		productService.findOne(id);
		return productService.updateProduct(product);
	}
	
}
