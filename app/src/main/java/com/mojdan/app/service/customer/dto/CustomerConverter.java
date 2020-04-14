package com.mojdan.app.service.customer.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mojdan.app.model.user.Customer;

@Component
public class CustomerConverter {

	public CustomerDTO toDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(customer, customerDTO);
		if (customer.getUser() != null) {
			customerDTO.setUsername(customer.getUser().getUsername());
			customerDTO.setPhonenumber(customer.getUser().getPhonenumber());
			customerDTO.setStatus(customer.getUser().getStatus());
		}
		return customerDTO;
	}

	public Customer toEntity(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(customerDTO, customer);
		return customer;
	}

	public List<CustomerDTO> toDTOs(List<Customer> list) {
		List<CustomerDTO> listDTOs = new ArrayList<CustomerDTO>();
		list.forEach((entity) -> {
			listDTOs.add(toDTO(entity));
		});
		return listDTOs;
	}

	public List<Customer> toEntities(List<CustomerDTO> listDTOs) {
		List<Customer> list = new ArrayList<Customer>();
		listDTOs.forEach((dto) -> {
			list.add(toEntity(dto));
		});
		return list;
	}
}
