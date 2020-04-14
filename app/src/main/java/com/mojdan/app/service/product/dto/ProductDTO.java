package com.mojdan.app.service.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.mojdan.app.model.category.Category;
import com.mojdan.app.model.storecom.Storecom;

public class ProductDTO {

	private Long id;

	private String name;
	
	private String description;

	private Category category;

	private Storecom store;

	private BigDecimal price;

	private String pictureUrl;

	private boolean isActive;

	private Date creationDate;

	public ProductDTO() {
	}

	public ProductDTO(Long id, String name, Category category, Storecom store, BigDecimal price, String pictureUrl,
			boolean isActive, Date creationDate, String description) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.store = store;
		this.price = price;
		this.pictureUrl = pictureUrl;
		this.isActive = isActive;
		this.creationDate = creationDate;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Storecom getStore() {
		return store;
	}

	public void setStore(Storecom store) {
		this.store = store;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
