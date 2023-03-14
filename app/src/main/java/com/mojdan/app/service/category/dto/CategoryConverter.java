package com.mojdan.app.service.category.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mojdan.app.model.category.Category;

@Component
public class CategoryConverter {
	public CategoryDTO toDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(category, categoryDTO);
		return categoryDTO;
	}

	public Category toEntity(CategoryDTO categoryDTO) {
		Category category = new Category();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(categoryDTO, category);
		return category;
	}

	public List<CategoryDTO> toDTOs(List<Category> list) {
		List<CategoryDTO> listDTOs = new ArrayList<CategoryDTO>();
		list.forEach((entity) -> {
			listDTOs.add(toDTO(entity));
		});
		return listDTOs;
	}

	public List<Category> toEntities(List<CategoryDTO> listDTOs) {
		List<Category> list = new ArrayList<Category>();
		listDTOs.forEach((dto) -> {
			list.add(toEntity(dto));
		});
		return list;
	}
}
