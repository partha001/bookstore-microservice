package com.partha.userService.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAddressRequest {

	private Integer userId;
	private String addressLine1;
	private String addressLine2;
	private String state;
	private String country;
	private Integer pincode;


}
