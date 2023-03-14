package com.mojdan.app.service.customer.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.mojdan.app.model.address.Address;
import com.mojdan.app.model.user.Status;

public class CustomerDTO {

	private Long id;

	private String username;
	
	private String email;

	private String firstName;

	private String lastName;

	private String phonenumber;

	@Enumerated(EnumType.STRING)
	private Status status;

	private Address primaryAddress;

	public CustomerDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Address getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(Address primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
