package com.mojdan.app.service.store;

import com.mojdan.app.service.store.dto.StoreDTO;

public interface StoreService {

	public Iterable<StoreDTO> getAllStores();

	public StoreDTO findOne(long id);

	public StoreDTO save(StoreDTO store);

	public void delete(long id);

	public StoreDTO updateStore(StoreDTO storeDTO);

}
