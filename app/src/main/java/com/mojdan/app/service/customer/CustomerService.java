package com.mojdan.app.service.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mojdan.app.service.customer.dto.CustomerDTO;

public interface CustomerService {

	public Page<CustomerDTO> findAllCustomers(Pageable pageable);
	
	public CustomerDTO findByUsername(String username);

	public CustomerDTO findOne(Long id);

	public void delete(Long id);

	public CustomerDTO updateCustomer(CustomerDTO customer);

	public CustomerDTO save(CustomerDTO customer);

}
