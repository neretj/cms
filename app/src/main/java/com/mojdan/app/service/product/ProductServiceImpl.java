package com.mojdan.app.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mojdan.app.model.product.Product;
import com.mojdan.app.model.product.ProductRepository;
import com.mojdan.app.service.product.dto.ProductConverter;
import com.mojdan.app.service.product.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Iterable<ProductDTO> getAllProducts() {
		List<Product> list = productRepository.findAll();
		return productConverter.toDTOs(list);
	}
	
	@Override
	public Page<ProductDTO> getAllProducts(Pageable pageRequest) {
		Page<Product> page = productRepository.findAll(pageRequest);
		Page<ProductDTO> pageDTO = page.map(productConverter::toDTO);	
		return pageDTO;
	}

	@Override
	public ProductDTO findOne(long id) {
		Optional<Product> product = productRepository.findById(id);
		return productConverter.toDTO(product.get());
	}

	@Override
	public ProductDTO save(ProductDTO product) {
		Product saved = productRepository.save(productConverter.toEntity(product));
		return productConverter.toDTO(saved);
	}

	@Override
	public void delete(long id) {
		Optional<Product> entity = productRepository.findById(id);
		if (entity.isPresent()) {
			productRepository.delete(entity.get());			
		}
	}

	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) {
		Product product = productConverter.toEntity(productDTO);
		ProductDTO dto = productConverter.toDTO(productRepository.save(product));
		return dto;
	}

}
