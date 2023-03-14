package com.mojdan.app.service.notification.dto;

import java.util.Date;

import com.mojdan.app.model.notification.NotificationStatus;
import com.mojdan.app.model.notification.NotificationType;
import com.mojdan.app.model.shop.Shop;

public class NotificationDTO {

	private Long id;
	private Shop shop;
	private String description;
	private NotificationStatus status;
	private NotificationType type;
	private Date creationDate;

	public NotificationDTO(Long id, Shop shop, String description, NotificationStatus status,
			NotificationType type, Date creationDate) {
		this.id = id;
		this.shop = shop;
		this.description = description;
		this.status = status;
		this.type = type;
		this.creationDate = creationDate;
	}

	public NotificationDTO() {
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
