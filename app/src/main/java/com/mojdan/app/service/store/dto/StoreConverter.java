package com.mojdan.app.service.store.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mojdan.app.model.storecom.Storecom;

@Component
public class StoreConverter {
	public StoreDTO toDTO(Storecom store) {
		StoreDTO storeDTO = new StoreDTO();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(store, storeDTO);
		return storeDTO;
	}

	public Storecom toEntity(StoreDTO storeDTO) {
		Storecom store = new Storecom();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(storeDTO, store);
		return store;
	}

	public List<StoreDTO> toDTOs(List<Storecom> list) {
		List<StoreDTO> listDTOs = new ArrayList<StoreDTO>();
		list.forEach((entity) -> {
			listDTOs.add(toDTO(entity));
		});
		return listDTOs;
	}

	public List<Storecom> toEntities(List<StoreDTO> listDTOs) {
		List<Storecom> list = new ArrayList<Storecom>();
		listDTOs.forEach((dto) -> {
			list.add(toEntity(dto));
		});
		return list;
	}
}
