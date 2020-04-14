package com.mojdan.app.service.product.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mojdan.app.model.product.Product;

@Component
public class ProductConverter {

	public ProductDTO toDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(product, productDTO);
		return productDTO;
	}

	public Product toEntity(ProductDTO productDTO) {
		Product product = new Product();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(productDTO, product);
		return product;
	}

	public List<ProductDTO> toDTOs(List<Product> list) {
		List<ProductDTO> listDTOs = new ArrayList<ProductDTO>();
		list.forEach((entity) -> {
			listDTOs.add(toDTO(entity));
		});
		return listDTOs;
	}

	public List<Product> toEntities(List<ProductDTO> listDTOs) {
		List<Product> list = new ArrayList<Product>();
		listDTOs.forEach((dto) -> {
			list.add(toEntity(dto));
		});
		return list;
	}

}
