package com.mojdan.app.model.shop;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ShopRepository extends CrudRepository<Shop, Long> {

	List<Shop> findAll();

}
