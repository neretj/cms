package com.mojdan.app.service.clientOrder;

import java.util.List;

import com.mojdan.app.service.clientOrder.dto.ClientOrderDTO;

public interface ClientOrderService {

	public List<ClientOrderDTO> getAllClientOrders();

	public List<ClientOrderDTO> getLastClientOrders();

	public ClientOrderDTO getClientOrder(long id);

	public List<ClientOrderDTO> getAllClientOrdersByCustomerId(long id);

	public ClientOrderDTO save(ClientOrderDTO clientOrder);

}
