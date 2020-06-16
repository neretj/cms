package com.mojdan.app.service.shop.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mojdan.app.model.shop.Shop;

@Component
public class ShopConverter {
	public ShopDTO toDTO(Shop store) {
		ShopDTO storeDTO = new ShopDTO();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(store, storeDTO);
		return storeDTO;
	}

	public Shop toEntity(ShopDTO storeDTO) {
		Shop store = new Shop();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(storeDTO, store);
		return store;
	}

	public List<ShopDTO> toDTOs(List<Shop> list) {
		List<ShopDTO> listDTOs = new ArrayList<ShopDTO>();
		list.forEach((entity) -> {
			listDTOs.add(toDTO(entity));
		});
		return listDTOs;
	}

	public List<Shop> toEntities(List<ShopDTO> listDTOs) {
		List<Shop> list = new ArrayList<Shop>();
		listDTOs.forEach((dto) -> {
			list.add(toEntity(dto));
		});
		return list;
	}
}
