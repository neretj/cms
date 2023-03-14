package com.mojdan.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mojdan.app.service.notification.NotificationService;
import com.mojdan.app.service.notification.dto.NotificationDTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/notifications")
public class NotificationController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private NotificationService notificationService;

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_ADMIN')")
	public Iterable<NotificationDTO> findOpenNotifications(Long storeId) {
		Iterable<NotificationDTO> list = notificationService.findOpenNotifications(storeId);
		return list;
	}

}
