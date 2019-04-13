package com.partha.adminApplication.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class BookModel {
	
	@NotBlank(message="book name cannot be blank")
	private String bookName;
	
	private String author;
	
	@NotBlank(message="category can not be blank")
	private String category;
	
	
	@NotNull(message="available units can not be empty")
	@Min(value=1,message="available unit  should be positive")
	@Max(value=999,message="max available unit cant be more than 999")
	private Integer availableUnits;
	
	@NotNull(message="price can not be left empty")
	@DecimalMin("1.00")
	private BigDecimal price;
	
}