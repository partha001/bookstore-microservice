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
	
	private String publisher;
	
	private String language;
	
	private int pages;
	
	private String isbn;
	
	private String format;
	
	private String description;
	
	private String publicationDate;
	
	private String image;
	
}