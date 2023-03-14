package com.mojdan.app.service.shop.dto;

import java.util.List;

import com.mojdan.app.model.address.Address;
import com.mojdan.app.model.product.Product;

public class ShopDTO {

	private Long id;

	private String name;

	private Address address;

	private List<Product> products;

	public ShopDTO() {
	}

	public ShopDTO(Long id, String name, Address address, List<Product> products) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
