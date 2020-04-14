package com.mojdan.app.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.mojdan.app.service.customer.CustomerService;
import com.mojdan.app.service.customer.dto.CustomerDTO;
import com.mojdan.app.service.customer.dto.PageCustomerDTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/customers")
public class UserController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/principal")
	public Principal user(Principal user) {
		return user;
	}
	
	@GetMapping("/find/all")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public PageCustomerDTO findAllCustomers(@RequestParam("page") int page, @RequestParam("size") int size) {
		LOGGER.info("Finding all users...");		
		PageCustomerDTO pageCustomer = new PageCustomerDTO();
		Pageable pageable = PageRequest.of(page, size);
		Page<CustomerDTO> pageDTO = customerService.findAllCustomers(pageable);
		pageCustomer.setPage(page);
		pageCustomer.setSize(size);
		pageCustomer.setList(pageDTO.getContent());
		pageCustomer.setTotal(pageDTO.getTotalElements());
		
		return pageCustomer;
	}

	@GetMapping("/find/username")
	public CustomerDTO findByUsername(@RequestParam(value = "username") String username) {
		LOGGER.trace("Finding user by name...");
		return customerService.findByUsername(username);
	}

	@GetMapping("/find/id")
	public CustomerDTO findOne(@RequestParam(value = "id") Long id) {
		LOGGER.info("Finding user by id", id);
		CustomerDTO customer = customerService.findOne(id);
		return customer;
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO create(@RequestBody CustomerDTO customer) {
		LOGGER.info("Creating user...", customer.toString());
		return customerService.save(customer);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		customerService.findOne(id);
		customerService.delete(id);
	}

	@PutMapping("/update/{id}")
	public CustomerDTO updateUser(@RequestBody CustomerDTO customer, @PathVariable Long id) {
		LOGGER.info("Updating user...", id);
		customerService.findOne(id);
		return customerService.updateCustomer(customer);
	}
}
