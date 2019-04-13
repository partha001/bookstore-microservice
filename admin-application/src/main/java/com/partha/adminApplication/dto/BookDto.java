package com.partha.adminApplication.dto;

import java.math.BigDecimal;

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
public class BookDto {
	
	private int id;
	
	private String title;
	
	private String author;
	
	private String category;
		
	private Integer availableUnits;
	
	private BigDecimal price;
	
	private String status;
	
}