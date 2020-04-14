package com.mojdan.app.controller;

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

import com.mojdan.app.service.clientOrder.ClientOrderService;
import com.mojdan.app.service.clientOrder.dto.ClientOrderDTO;
import com.mojdan.app.service.customer.dto.CustomerDTO;
import com.mojdan.app.service.customer.dto.PageCustomerDTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/orders")
public class OrderController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ClientOrderService clientOrderService;

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public Iterable<ClientOrderDTO> findAllProducts() {
		Iterable<ClientOrderDTO> list = clientOrderService.getAllClientOrders();
		return list;
	}

	@GetMapping("/last")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public Iterable<ClientOrderDTO> findLastOrders() {
		Iterable<ClientOrderDTO> list = clientOrderService.getLastClientOrders();
		return list;
	}

//	@GetMapping("/find/id")
//	public ClientOrderDTO findOne(@RequestParam(value = "id") Long id) {
//		LOGGER.info("Finding clientOrder by id", id);
//		ClientOrderDTO clientOrder = clientOrderService.findOne(id);
//		return clientOrder;
//	}
//
//	@PostMapping("/create")
//	@ResponseStatus(HttpStatus.CREATED)
//	public ClientOrderDTO create(@RequestBody ClientOrderDTO clientOrder) {
//		LOGGER.info("Creating clientOrder...", clientOrder.toString());
//		return clientOrderService.save(clientOrder);
//	}
//
//	@DeleteMapping("/{id}")
//	public void delete(@PathVariable Long id) {
//		clientOrderService.findOne(id);
//		clientOrderService.delete(id);
//	}
//
//	@PutMapping("/update/{id}")
//	public ClientOrderDTO updateUser(@RequestBody ClientOrderDTO clientOrder, @PathVariable Long id) {
//		LOGGER.info("Updating clientOrder...", id);
//		clientOrderService.findOne(id);
//		return clientOrderService.updateProduct(clientOrder);
//	}

}
