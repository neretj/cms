package com.mojdan.app.service.customer.dto;

import java.util.List;

public class PageCustomerDTO {

	private Integer page;

	private Integer size;

	private Long total;

	private List<CustomerDTO> list;

	public PageCustomerDTO() {
	}

	public PageCustomerDTO(Integer page, Integer size, Long total, List<CustomerDTO> list) {
		this.page = page;
		this.size = size;
		this.total = total;
		this.list = list;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<CustomerDTO> getList() {
		return list;
	}

	public void setList(List<CustomerDTO> list) {
		this.list = list;
	}

}
