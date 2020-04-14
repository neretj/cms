package com.mojdan.app.service.clientOrder.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mojdan.app.model.order.ClientOrder;

@Component
public class ClientOrderConverter {

	public ClientOrderDTO toDTO(ClientOrder ClientOrder) {
		ClientOrderDTO clientOrderDTO = new ClientOrderDTO();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(ClientOrder, clientOrderDTO);
		return clientOrderDTO;
	}

	public ClientOrder toEntity(ClientOrderDTO clientOrderDTO) {
		ClientOrder clientOrder = new ClientOrder();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(clientOrderDTO, clientOrder);
		return clientOrder;
	}

	public List<ClientOrderDTO> toDTOs(List<ClientOrder> list) {
		List<ClientOrderDTO> listDTOs = new ArrayList<ClientOrderDTO>();
		list.forEach((entity) -> {
			listDTOs.add(toDTO(entity));
		});
		return listDTOs;
	}

	public List<ClientOrder> toEntities(List<ClientOrderDTO> listDTOs) {
		List<ClientOrder> list = new ArrayList<ClientOrder>();
		listDTOs.forEach((dto) -> {
			list.add(toEntity(dto));
		});
		return list;
	}

}
