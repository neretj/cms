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
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	private User user;

	@NotNull(message = "Firstname is required.")
	@Basic(optional = false)
	private String firstName;

	@NotNull(message = "Surname is required.")
	@Basic(optional = false)
	private String lastName;

	@OneToOne
	private Address primaryAddress;
	
	@OneToMany
	private List<Address> listAddresses;

	@OneToMany(mappedBy = "customer")
	private List<ClientOrder> clientOrders;

	public Customer() {
	}

	public Customer(User user, @NotNull(message = "Firstname is required.") String firstName,
			@NotNull(message = "Surname is required.") String lastName, Address primaryAddress) {
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryAddress = primaryAddress;
	}

	public List<ClientOrder> getClientOrders() {
		return clientOrders;
	}

	public void setClientOrders(List<ClientOrder> clientOrders) {
		this.clientOrders = clientOrders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(Address primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public List<Address> getListAddresses() {
		return listAddresses;
	}

	public void setListAddresses(List<Address> listAddresses) {
		this.listAddresses = listAddresses;
	}

}
