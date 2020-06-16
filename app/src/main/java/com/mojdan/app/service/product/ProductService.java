package com.mojdan.app.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mojdan.app.service.product.dto.ProductDTO;

public interface ProductService {

	public Iterable<ProductDTO> getAllProducts();

	public Page<ProductDTO> getAllProducts(Pageable pageRequest);

	public ProductDTO findOne(long id);

	public ProductDTO save(ProductDTO product);

	public void delete(long id);

	public ProductDTO updateProduct(ProductDTO productDTO);
}
