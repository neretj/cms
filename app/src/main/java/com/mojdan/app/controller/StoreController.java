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

import com.mojdan.app.service.store.StoreService;
import com.mojdan.app.service.store.dto.StoreDTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/stores")
public class StoreController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private StoreService storeService;

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public Iterable<StoreDTO> findAllStores(@RequestParam("page") int page, @RequestParam("size") int size) {
		Pageable pageable = PageRequest.of(page, size);
		LOGGER.info("Finding all users...");
		Iterable<StoreDTO> list = storeService.getAllStores();
		LOGGER.info("...returning users ", list.toString());
		return list;
	}

	@GetMapping("/find/id")
	public StoreDTO findOne(@RequestParam(value = "id") Long id) {
		LOGGER.info("Finding store by id", id);
		StoreDTO store = storeService.findOne(id);
		return store;
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public StoreDTO create(@RequestBody StoreDTO store) {
		LOGGER.info("Creating store...", store.toString());
		return storeService.save(store);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		storeService.findOne(id);
		storeService.delete(id);
	}

	@PutMapping("/update/{id}")
	public StoreDTO updateUser(@RequestBody StoreDTO store, @PathVariable Long id) {
		LOGGER.info("Updating store...", id);
		storeService.findOne(id);
		return storeService.updateStore(store);
	}
}
