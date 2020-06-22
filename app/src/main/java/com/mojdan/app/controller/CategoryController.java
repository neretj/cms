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

import com.mojdan.app.service.category.CategoryService;
import com.mojdan.app.service.category.dto.CategoryDTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/category")
public class CategoryController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public Iterable<CategoryDTO> findAllCategories() {
		Iterable<CategoryDTO> list = categoryService.getAllCategories();
		return list;
	}

	@GetMapping("/all/page")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public Iterable<CategoryDTO> findAllCateogires(@RequestParam("page") int page, @RequestParam("size") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Iterable<CategoryDTO> list = categoryService.getAllCategories(pageable);
		return list;
	}

	@GetMapping("/find/id")
	public CategoryDTO findOne(@RequestParam(value = "id") Long id) {
		LOGGER.info("Finding category by id", id);
		CategoryDTO category = categoryService.findOne(id);
		return category;
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryDTO create(@RequestBody CategoryDTO category) {
		LOGGER.info("Creating category...", category.toString());
		return categoryService.save(category);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		categoryService.findOne(id);
		categoryService.delete(id);
	}

	@PutMapping("/update/{id}")
	public CategoryDTO updateUser(@RequestBody CategoryDTO category, @PathVariable Long id) {
		LOGGER.info("Updating category...", id);
		categoryService.findOne(id);
		return categoryService.updateCategory(category);
	}

}
