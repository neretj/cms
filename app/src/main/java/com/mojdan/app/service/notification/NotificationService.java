package com.mojdan.app.service.notification;

import com.mojdan.app.service.notification.dto.NotificationDTO;

public interface NotificationService {

	Iterable<NotificationDTO> findOpenNotifications(Long userId);

}
