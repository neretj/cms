package com.mojdan.app.model.order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ClientOrderRepository extends CrudRepository<ClientOrder, Long> {

	List<ClientOrder> findAll();
	
	Page<ClientOrder> findAll(Pageable pageable);

	Optional<ClientOrder> findById(Long id);
	
	List<ClientOrder> findByCustomer(Long id);
}
