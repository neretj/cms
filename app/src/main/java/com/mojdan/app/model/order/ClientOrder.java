package com.mojdan.app.model.order;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.mojdan.app.model.product.Product;
import com.mojdan.app.model.user.Customer;
import com.mojdan.app.model.user.User;

@Entity
public class ClientOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToMany(fetch=FetchType.EAGER) 
	private List<Product> products;

	private Date orderDate;

	@ManyToOne
	private Customer customer;

	public ClientOrder(List<Product> products, Date orderDate, Customer user) {
		super();
		this.products = products;
		this.orderDate = orderDate;
		this.customer = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public User getUser() {
		return customer;
	}

	public void setUser(Customer customer) {
		this.customer = customer;
	}

}
