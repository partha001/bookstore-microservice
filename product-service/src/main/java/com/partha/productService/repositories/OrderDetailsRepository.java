package com.partha.productService.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.partha.productService.entities.OrderDetail;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetail, Integer>,CustomOrderDetailsRepository  {
	
	
}
