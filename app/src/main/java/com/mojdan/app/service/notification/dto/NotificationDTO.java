package com.mojdan.app.service.notification.dto;

import java.util.Date;

import com.mojdan.app.model.notification.NotificationStatus;
import com.mojdan.app.model.notification.NotificationType;
import com.mojdan.app.model.storecom.Storecom;

public class NotificationDTO {

	private Long id;
	private Storecom storecom;
	private String description;
	private NotificationStatus status;
	private NotificationType type;
	private Date creationDate;

	public NotificationDTO(Long id, Storecom storecom, String description, NotificationStatus status,
			NotificationType type, Date creationDate) {
		this.id = id;
		this.storecom = storecom;
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

	public Storecom getStorecom() {
		return storecom;
	}

	public void setStorecom(Storecom storecom) {
		this.storecom = storecom;
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
