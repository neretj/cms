package com.mojdan.app.service.product.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.mojdan.app.model.category.Category;
import com.mojdan.app.model.shop.Shop;
import com.mojdan.app.service.tag.dto.TagDTO;

public class ProductDTO {

	private Long id;

	private String name;
	
	private String description;

	private Category category;

	private Shop shop;

	private BigDecimal price;

	private BigDecimal priceWithoutTaxes;
	
	private BigDecimal taxRate;
	
	private BigDecimal discount;

	private String pictureUrl;

	private boolean isActive;

	private Date creationDate;

	private List<TagDTO> tags;
	
	private String inventoryCode;
	
	private Long stock;
	
	public ProductDTO() {
	}

	public ProductDTO(Long id, String name, Category category, Shop shop, BigDecimal price, String pictureUrl,
			boolean isActive, Date creationDate, String description) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.shop = shop;
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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
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

	public List<TagDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}

	public BigDecimal getPriceWithoutTaxes() {
		return priceWithoutTaxes;
	}

	public void setPriceWithoutTaxes(BigDecimal priceWithoutTaxes) {
		this.priceWithoutTaxes = priceWithoutTaxes;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

}
