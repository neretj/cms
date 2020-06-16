package com.mojdan.app.service.shop;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mojdan.app.model.shop.Shop;
import com.mojdan.app.model.shop.ShopRepository;
import com.mojdan.app.service.shop.dto.ShopConverter;
import com.mojdan.app.service.shop.dto.ShopDTO;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopConverter storeConverter;

	@Autowired
	private ShopRepository storeRepository;

	@Override
	public Iterable<ShopDTO> getAllStores() {
		List<Shop> list = storeRepository.findAll();
		return storeConverter.toDTOs(list);
	}

	@Override
	public ShopDTO findOne(long id) {
		Optional<Shop> store = storeRepository.findById(id);
		return storeConverter.toDTO(store.get());
	}

	@Override
	public ShopDTO save(ShopDTO store) {
		Shop saved = storeRepository.save(storeConverter.toEntity(store));
		return storeConverter.toDTO(saved);
	}

	@Override
	public void delete(long id) {
		Optional<Shop> entity = storeRepository.findById(id);
		if (entity.isPresent()) {
			storeRepository.delete(entity.get());
		}
	}

	@Override
	public ShopDTO updateStore(ShopDTO storeDTO) {
		Shop store = storeConverter.toEntity(storeDTO);
		ShopDTO dto = storeConverter.toDTO(storeRepository.save(store));
		return dto;
	}
}
