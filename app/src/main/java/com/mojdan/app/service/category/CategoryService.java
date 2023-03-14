package com.mojdan.app.service.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mojdan.app.service.category.dto.CategoryDTO;

public interface CategoryService {

	public Iterable<CategoryDTO> getAllCategories();

	public Page<CategoryDTO> getAllCategories(Pageable pageRequest);

	public CategoryDTO findOne(long id);

	public CategoryDTO save(CategoryDTO category);

	public void delete(long id);

	public CategoryDTO updateCategory(CategoryDTO categoryDTO);
	
}
