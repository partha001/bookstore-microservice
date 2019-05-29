package com.partha.productService.dto;

import java.util.Date;

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
public class AddToCartDto {
	
	private Integer cartItemId;
	private Integer bookId;
	private Integer userId;
	private Integer quantity;
	private Date insertDate;
	private Date updateDate;

}
