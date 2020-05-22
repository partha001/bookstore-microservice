package com.partha.productService.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.partha.productService.entities.OrderMaster;

@Repository
public interface OrderMasterRepository extends CrudRepository<OrderMaster, Integer> {
	
	
}
