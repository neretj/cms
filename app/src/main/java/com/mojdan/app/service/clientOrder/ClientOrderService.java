package com.mojdan.app.service.clientOrder;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mojdan.app.service.clientOrder.dto.ClientOrderDTO;

public interface ClientOrderService {

	public List<ClientOrderDTO> getAllClientOrders();

	public Page<ClientOrderDTO> getAllClientOrders(Pageable pageRequest);

	public Page<ClientOrderDTO> getLastClientOrders();

	public ClientOrderDTO getClientOrder(long id);

	public List<ClientOrderDTO> getAllClientOrdersByCustomerId(long id);

	public ClientOrderDTO save(ClientOrderDTO clientOrder);

}
