package com.mojdan.app.service.clientOrder.dto;

import java.util.Date;
import java.util.List;

import com.mojdan.app.model.order.OrderStatus;
import com.mojdan.app.service.customer.dto.CustomerDTO;
import com.mojdan.app.service.product.dto.ProductDTO;

public class ClientOrderDTO {

	private Long id;

	private List<ProductDTO> products;

	private Date orderDate;

	private CustomerDTO customer;

	private Double totalAmount;

	private OrderStatus status;

	public ClientOrderDTO() {
	}

	public ClientOrderDTO(Long id, List<ProductDTO> products, Date orderDate, CustomerDTO customer, Double totalAmount,
			OrderStatus status) {
		this.id = id;
		this.products = products;
		this.orderDate = orderDate;
		this.customer = customer;
		this.totalAmount = totalAmount;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}
