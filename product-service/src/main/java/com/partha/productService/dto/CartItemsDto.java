package com.partha.productService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemsDto {

	private Integer  cartId;
	private Integer  userId;
	private Integer  bookId;	
	private String   title;
	private String   description;
	private String   author;
	private Integer  quantity;
	private String   publisher;
	private String   category;
	private String   image;

	
}
