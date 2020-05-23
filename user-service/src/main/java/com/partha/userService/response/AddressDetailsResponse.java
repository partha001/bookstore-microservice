package com.partha.userService.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDetailsResponse {
	
	private Integer userId;
	private Integer addressId;
	private String addressLine1;
	private String addressLine2;
	private Integer pincode;
	private String state;
	private String country;
}
