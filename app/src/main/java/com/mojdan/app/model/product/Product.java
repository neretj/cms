package com.mojdan.app.model.product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.mojdan.app.model.category.Category;
import com.mojdan.app.model.fileinfo.FileInfo;
import com.mojdan.app.model.order.ClientOrder;
import com.mojdan.app.model.shop.Shop;
import com.mojdan.app.model.tag.Tag;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Product name is required.")
	@Basic(optional = false)
	private String name;

	private String description;

	@ManyToOne
	private Category category;

	@ManyToOne
	private Shop shop;

	private BigDecimal price;
	
	private BigDecimal priceWithoutTaxes;
	
	private BigDecimal taxRate;
	
	private BigDecimal discount;

	@OneToMany
	private List<FileInfo> pictureUrls;

	private boolean isActive;

	private Date creationDate;
	
	private String inventoryCode;
	
	private Long stock;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Product_Tag", 
        joinColumns = { @JoinColumn(name = "product_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
	private List<Tag> tags;
	
	@ManyToMany(mappedBy = "products", cascade = { CascadeType.ALL })
	private List<ClientOrder> clientOrder;

	public Product() {
	}

	public Product(@NotNull(message = "Product name is required.") String name, Category category, Shop shop,
			BigDecimal price, List<FileInfo> pictureUrls, boolean isActive, Date creationDate) {
		this.name = name;
		this.category = category;
		this.shop = shop;
		this.price = price;
		this.pictureUrls = pictureUrls;
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

	public List<FileInfo> getPictureUrls() {
		return pictureUrls;
	}

	public void setPictureUrl(List<FileInfo> pictureUrls) {
		this.pictureUrls = pictureUrls;
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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPictureUrls(List<FileInfo> pictureUrls) {
		this.pictureUrls = pictureUrls;
	}

}
