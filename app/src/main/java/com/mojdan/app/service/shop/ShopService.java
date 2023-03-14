package com.mojdan.app.service.shop;

import com.mojdan.app.service.shop.dto.ShopDTO;

public interface ShopService {

	public Iterable<ShopDTO> getAllStores();

	public ShopDTO findOne(long id);

	public ShopDTO save(ShopDTO store);

	public void delete(long id);

	public ShopDTO updateStore(ShopDTO storeDTO);

}
