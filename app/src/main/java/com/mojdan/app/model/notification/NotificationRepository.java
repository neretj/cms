package com.mojdan.app.model.notification;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mojdan.app.model.shop.Shop;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long>{

	List<Notification> findAll();
	
	List<Notification> findAllByShopIdAndStatus(Long id, NotificationStatus status);
}
