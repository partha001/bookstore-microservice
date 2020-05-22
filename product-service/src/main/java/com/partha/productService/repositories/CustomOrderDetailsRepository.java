package com.partha.productService.repositories;

import java.util.List;

import com.partha.productService.dto.OrderHistoryDto;

public interface CustomOrderDetailsRepository {

	public List<OrderHistoryDto> getOrderHistory(int userId);
		
}
