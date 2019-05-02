package com.partha.userService.dto;

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
public class ForgotPasswordDto {
	
	private String email;
	private String securityQuestion;
	private String securityAnswer;

}
