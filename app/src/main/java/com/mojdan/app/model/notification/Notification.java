package com.mojdan.app.model.notification;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.mojdan.app.model.shop.Shop;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Store is required.")
	@Basic(optional = false)
	@ManyToOne
	private Shop shop;

	@NotNull(message = "Description is required.")
	@Basic(optional = false)
	private String description;

	@NotNull(message = "Status is required.")
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private NotificationStatus status;

	@NotNull(message = "Type is required.")
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private NotificationType type;

	private Date creationDate;

	public Notification() {
	}

	public Notification(Shop shop, String description, NotificationStatus status, NotificationType type) {
		this.shop = shop;
		this.description = description;
		this.status = status;
		this.type = type;
		this.creationDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
