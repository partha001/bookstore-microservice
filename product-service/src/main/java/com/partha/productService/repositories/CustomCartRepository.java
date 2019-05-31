package com.partha.productService.repositories;

import java.util.List;

import com.partha.productService.dto.CartItemsDto;

public interface CustomCartRepository {

	//public List<CartItemsDto> findByUserId(Integer userId);
	public List<CartItemsDto> findByUserId(Integer userId);
		
}
