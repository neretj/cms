package com.mojdan.app.model.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.mojdan.app.model.order.ClientOrder;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id") 
public class Customer extends User {

	private String test;
	
	@OneToMany(mappedBy = "customer")
	private List<ClientOrder> clientOrders;
	
	public Customer() {
		super();
	}

	public Customer(String username, String password, String name, String surname, String test) {
		super(username, password, name, surname);
		this.test = test;
	}

	public List<ClientOrder> getClientOrders() {
		return clientOrders;
	}

	public void setClientOrders(List<ClientOrder> clientOrders) {
		this.clientOrders = clientOrders;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
	
}
