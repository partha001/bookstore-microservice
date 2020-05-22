package com.partha.productService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryDto {
	
	private Integer orderId;
	private String deliveryAddress;
	private String orderDate;
	private Integer orderDetailsId;
	private Integer bookid;
	private Integer quantity;
	private String title;
	private String author;
	private double price;
	private String image;
	private String description;	

}
