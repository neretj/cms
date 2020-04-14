package com.mojdan.app.service.store;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mojdan.app.model.storecom.Storecom;
import com.mojdan.app.model.storecom.StorecomRepository;
import com.mojdan.app.service.store.dto.StoreConverter;
import com.mojdan.app.service.store.dto.StoreDTO;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreConverter storeConverter;

	@Autowired
	private StorecomRepository storeRepository;

	@Override
	public Iterable<StoreDTO> getAllStores() {
		List<Storecom> list = storeRepository.findAll();
		return storeConverter.toDTOs(list);
	}

	@Override
	public StoreDTO findOne(long id) {
		Optional<Storecom> store = storeRepository.findById(id);
		return storeConverter.toDTO(store.get());
	}

	@Override
	public StoreDTO save(StoreDTO store) {
		Storecom saved = storeRepository.save(storeConverter.toEntity(store));
		return storeConverter.toDTO(saved);
	}

	@Override
	public void delete(long id) {
		Optional<Storecom> entity = storeRepository.findById(id);
		if (entity.isPresent()) {
			storeRepository.delete(entity.get());
		}
	}

	@Override
	public StoreDTO updateStore(StoreDTO storeDTO) {
		Storecom store = storeConverter.toEntity(storeDTO);
		StoreDTO dto = storeConverter.toDTO(storeRepository.save(store));
		return dto;
	}
}
