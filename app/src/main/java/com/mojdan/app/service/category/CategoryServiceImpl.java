package com.mojdan.app.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mojdan.app.model.category.Category;
import com.mojdan.app.model.category.CategoryRepository;
import com.mojdan.app.service.category.dto.CategoryConverter;
import com.mojdan.app.service.category.dto.CategoryDTO;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryConverter categoryConverter;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Iterable<CategoryDTO> getAllCategories() {
		List<Category> list = categoryRepository.findAll();
		return categoryConverter.toDTOs(list);
	}
	
	@Override
	public Page<CategoryDTO> getAllCategories(Pageable pageRequest) {
		Page<Category> page = categoryRepository.findAll(pageRequest);
		Page<CategoryDTO> pageDTO = page.map(categoryConverter::toDTO);	
		return pageDTO;
	}

	@Override
	public CategoryDTO findOne(long id) {
		Optional<Category> category = categoryRepository.findById(id);
		return categoryConverter.toDTO(category.get());
	}

	@Override
	public CategoryDTO save(CategoryDTO category) {
		Category saved = categoryRepository.save(categoryConverter.toEntity(category));
		return categoryConverter.toDTO(saved);
	}

	@Override
	public void delete(long id) {
		Optional<Category> entity = categoryRepository.findById(id);
		if (entity.isPresent()) {
			categoryRepository.delete(entity.get());			
		}
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
		Category category = categoryConverter.toEntity(categoryDTO);
		CategoryDTO dto = categoryConverter.toDTO(categoryRepository.save(category));
		return dto;
	}
	
}
