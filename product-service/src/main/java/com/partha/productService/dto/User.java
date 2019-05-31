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
public class User {
	
	private Integer id;
	
	private String firstname;
	private String lastname;
	
	//username is nothing but the email
	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean enabled;
	private boolean credentialsNonExpired;
	private String securityQuestion;
	private String securityAnswer;
	
	
		
}
