package com.mojdan.app.service.clientOrder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mojdan.app.model.order.ClientOrder;
import com.mojdan.app.model.order.ClientOrderRepository;
import com.mojdan.app.service.clientOrder.dto.ClientOrderConverter;
import com.mojdan.app.service.clientOrder.dto.ClientOrderDTO;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

	@Autowired
	private ClientOrderRepository clientOrderRepository;

	@Autowired
	private ClientOrderConverter clientOrderConverter;

	@Override
	public List<ClientOrderDTO> getAllClientOrders() {
		List<ClientOrder> list = clientOrderRepository.findAll();
		return clientOrderConverter.toDTOs(list);
	}

	@Override
	public List<ClientOrderDTO> getLastClientOrders() {
		Pageable pageable = PageRequest.of(0, 10);
		List<ClientOrder> list = clientOrderRepository.findAll(pageable);
		return clientOrderConverter.toDTOs(list);
	}

	@Override
	public ClientOrderDTO getClientOrder(long id) {
		Optional<ClientOrder> clientOrder = clientOrderRepository.findById(id);
		return clientOrderConverter.toDTO(clientOrder.get());
	}

	@Override
	public List<ClientOrderDTO> getAllClientOrdersByCustomerId(long id) {
		List<ClientOrder> list = clientOrderRepository.findByCustomer(id);
		return clientOrderConverter.toDTOs(list);
	}

	@Override
	public ClientOrderDTO save(ClientOrderDTO clientOrderDTO) {
		ClientOrder clientOrder = clientOrderConverter.toEntity(clientOrderDTO);
		ClientOrder saved = clientOrderRepository.save(clientOrder);
		return clientOrderConverter.toDTO(saved);
	}

}
