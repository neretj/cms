package com.mojdan.app.service.notification.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mojdan.app.model.notification.Notification;

@Component
public class NotificationConverter {
	public NotificationDTO toDTO(Notification notification) {
		NotificationDTO notificationDTO = new NotificationDTO();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(notification, notificationDTO);
		return notificationDTO;
	}

	public Notification toEntity(NotificationDTO notificationDTO) {
		Notification notification = new Notification();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(notificationDTO, notification);
		return notification;
	}

	public List<NotificationDTO> toDTOs(List<Notification> list) {
		List<NotificationDTO> listDTOs = new ArrayList<NotificationDTO>();
		list.forEach((entity) -> {
			listDTOs.add(toDTO(entity));
		});
		return listDTOs;
	}

	public List<Notification> toEntities(List<NotificationDTO> listDTOs) {
		List<Notification> list = new ArrayList<Notification>();
		listDTOs.forEach((dto) -> {
			list.add(toEntity(dto));
		});
		return list;
	}
}
