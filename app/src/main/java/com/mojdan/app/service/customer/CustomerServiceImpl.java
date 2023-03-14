package com.mojdan.app.service.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mojdan.app.model.user.Customer;
import com.mojdan.app.model.user.CustomerRepository;
import com.mojdan.app.model.user.User;
import com.mojdan.app.model.user.UserRepository;
import com.mojdan.app.service.customer.dto.CustomerConverter;
import com.mojdan.app.service.customer.dto.CustomerDTO;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerConverter customerConverter;

	@Override
	public Page<CustomerDTO> findAllCustomers(Pageable pageable) {
		Page<Customer> page = customerRepository.findAll(pageable);
		List<CustomerDTO> dtos = customerConverter.toDTOs(page.getContent());
		Page<CustomerDTO> pagedto = new PageImpl<CustomerDTO>(dtos);
		return pagedto;
	}

	@Override
	public CustomerDTO findByUsername(String username) {
		Optional<Customer> customer = customerRepository.findCustomerByUsername(username);
		CustomerDTO customerDTO = new CustomerDTO();
		if (customer.isPresent()) {
			customerDTO = customerConverter.toDTO(customer.get());
		}
		return customerDTO;
	}

	@Override
	public CustomerDTO findOne(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		CustomerDTO customerDTO = new CustomerDTO();
		if (customer.isPresent()) {
			customerDTO = customerConverter.toDTO(customer.get());
		}
		return customerDTO;
	}

	@Override
	public void delete(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.deleteById(user.get().getId());
		} else {
			// Exception user not found
		}
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
		Customer customer = customerConverter.toEntity(customerDTO);
		CustomerDTO dto = customerConverter.toDTO(customerRepository.save(customer));
		return dto;
	}

	@Override
	public CustomerDTO save(CustomerDTO customerDTO) {
		Customer customer = customerConverter.toEntity(customerDTO);
		CustomerDTO dto = customerConverter.toDTO(customerRepository.save(customer));
		return dto;
	}
}
