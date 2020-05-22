package com.partha.productService.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMasterDto {
	
	private Integer orderId;
	private String orderDate;
	private double totalAmount;
	private String deliverAddress;
	List<OrderDetailsDto> orderDetails;

}
