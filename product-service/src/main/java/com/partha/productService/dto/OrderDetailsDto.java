package com.partha.productService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {
	
	private Integer id;
	private Integer bookId;
	private Integer quantity;
	private String title;
	private String author;
	private double price;
	private String image;
	private String description;

}
