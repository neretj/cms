package com.mojdan.app.model.user;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.mojdan.app.model.address.Address;
import com.mojdan.app.model.shop.Shop;

@Entity
public class Seller {

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

	@ManyToOne
	private Shop storecom;

	public Seller() {
	}

	public Seller(User user, @NotNull(message = "Firstname is required.") String firstName,
			@NotNull(message = "Surname is required.") String lastName, Address primaryAddress, Shop storecom) {
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryAddress = primaryAddress;
		this.storecom = storecom;
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

	public Shop getStorecom() {
		return storecom;
	}

	public void setStorecom(Shop storecom) {
		this.storecom = storecom;
	}

}
