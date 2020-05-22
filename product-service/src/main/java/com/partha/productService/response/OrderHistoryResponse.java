package com.partha.productService.response;

import java.util.List;

import com.partha.productService.dto.OrderMasterDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryResponse {
	
	List<OrderMasterDto> orders;

}
