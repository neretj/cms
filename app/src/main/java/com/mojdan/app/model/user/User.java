package com.mojdan.app.model.user;

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
import com.mojdan.app.model.order.ClientOrder;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;
	
	private String password;
	
	@NotNull(message = "Name is required.")
	@Basic(optional = false)
	private String name;
	
	@NotNull(message = "Surname is required.")
	@Basic(optional = false)
	private String surname;
	
	@OneToOne
	private Address address;

	@OneToMany(mappedBy = "user")	
	private List<ClientOrder> clientOrders;
	
	public User() {
	}
	
	public User(String username, String password, String name, String surname) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}

	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public User(String name) {
		this.name = name;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<ClientOrder> getOrders() {
		return clientOrders;
	}

	public void setOrders(List<ClientOrder> clientOrders) {
		this.clientOrders = clientOrders;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ClientOrder> getClientOrders() {
		return clientOrders;
	}

	public void setClientOrders(List<ClientOrder> clientOrders) {
		this.clientOrders = clientOrders;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}
