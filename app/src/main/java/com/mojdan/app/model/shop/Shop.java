package com.mojdan.app.model.shop;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.mojdan.app.model.address.Address;
import com.mojdan.app.model.product.Product;

@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Name is required.")
	@Basic(optional = false)
	private String name;

	@OneToOne
	private Address address;

	@OneToMany(mappedBy = "shop")
	private List<Product> products;

	public Shop(Long id, @NotNull(message = "Name is required.") String name, Address address, List<Product> products) {
		super();
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
