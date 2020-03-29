package com.mojdan.app.model.order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ClientOrderRepository extends CrudRepository<ClientOrder, Long> {

	List<ClientOrder> findAll();

	Optional<ClientOrder> findById(Long id);
}
