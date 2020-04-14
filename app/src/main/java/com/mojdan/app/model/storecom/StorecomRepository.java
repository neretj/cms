package com.mojdan.app.model.storecom;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorecomRepository extends CrudRepository<Storecom, Long> {

	List<Storecom> findAll();

}
