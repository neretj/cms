package com.mojdan.app.model.storecom;

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
import com.mojdan.app.model.notification.Notification;
import com.mojdan.app.model.user.Seller;

@Entity
public class Storecom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Name is required.")
	@Basic(optional = false)
	private String name;

	@OneToOne
	private Address address;

	public Storecom() {
	}

	public Storecom(@NotNull(message = "Name is required.") String name, Address address) {
		this.name = name;
		this.address = address;
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

}
