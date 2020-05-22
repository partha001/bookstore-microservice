package com.partha.productService.repositories;

import java.util.List;
import java.util.Set;

import com.partha.productService.dto.CartItemsDto;

public interface CustomCartRepository {

	public List<CartItemsDto> findByUserId(Integer userId);
	
	public int updateActiveFlag(Set<Integer> cartIds);
		
}
