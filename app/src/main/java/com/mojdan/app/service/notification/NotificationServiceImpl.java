package com.mojdan.app.service.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mojdan.app.model.notification.Notification;
import com.mojdan.app.model.notification.NotificationRepository;
import com.mojdan.app.model.notification.NotificationStatus;
import com.mojdan.app.model.user.Seller;
import com.mojdan.app.model.user.SellerRepository;
import com.mojdan.app.service.notification.dto.NotificationConverter;
import com.mojdan.app.service.notification.dto.NotificationDTO;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepo;

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private NotificationConverter notificationConverter;

	@Override
	public Iterable<NotificationDTO> findOpenNotifications(Long storeId) {

		Iterable<NotificationDTO> list = new ArrayList<NotificationDTO>();
		List<Notification> notifications = notificationRepo.findAllByShopIdAndStatus(storeId, NotificationStatus.OPEN);
		list = notificationConverter.toDTOs(notifications);

		return list;
	}

}
