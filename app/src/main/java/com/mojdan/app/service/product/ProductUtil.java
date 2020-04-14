package com.mojdan.app.service.product;

import java.util.List;

import com.mojdan.app.model.product.Product;
import com.mojdan.app.service.product.dto.ProductDTO;

public class ProductUtil {

	public static Double getTotalAmountDTO(List<ProductDTO> products) {
		Double total = 0.0;
		for (ProductDTO prod : products) {
			total += prod.getPrice().doubleValue();
		}
		return total;
	}
	
	public static Double getTotalAmount(List<Product> products) {
		Double total = 0.0;
		for (Product prod : products) {
			total += prod.getPrice().doubleValue();
		}
		return total;
	}
}
