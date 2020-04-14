package com.mojdan.app.model.product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.mojdan.app.model.category.Category;
import com.mojdan.app.model.order.ClientOrder;
import com.mojdan.app.model.storecom.Storecom;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Product name is required.")
	@Basic(optional = false)
	private String name;

	@ManyToOne
	private Category category;

	@ManyToOne
	private Storecom storecom;

	private BigDecimal price;

	private String pictureUrl;

	private boolean isActive;

	private Date creationDate;

	@ManyToMany
	private List<ClientOrder> clientOrder;

	/*
	 * Id?: number; name?: string; price?: number; salePrice?: number; discount?:
	 * number; offerDate: date, pictures?: string; shortDetails?: string;
	 * description?: string; stock?: number; new?: boolean; sale?: boolean;
	 * category?: string; colors?: enum; size?: enum; tags?: enum; status [active,
	 * inactive, outofstock..], brand: string, open: boolean, unitsInStock,
	 * unitsOnOrder
	 */

	public Product() {
	}

	public Product(@NotNull(message = "Product name is required.") String name, Category category, Storecom storecom,
			BigDecimal price, String pictureUrl, boolean isActive, Date creationDate) {
		this.name = name;
		this.category = category;
		this.storecom = storecom;
		this.price = price;
		this.pictureUrl = pictureUrl;
		this.isActive = isActive;
		this.creationDate = creationDate;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ClientOrder> getClientOrder() {
		return clientOrder;
	}

	public void setClientOrder(List<ClientOrder> clientOrder) {
		this.clientOrder = clientOrder;
	}

	public Storecom getStorecom() {
		return storecom;
	}

	public void setStorecom(Storecom storecom) {
		this.storecom = storecom;
	}

}
