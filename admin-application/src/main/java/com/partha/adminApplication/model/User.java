package com.partha.adminApplication.model;

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
	
	//@NotBlank(message="username cant be left blank")
	private String username;
	
	//@NotNull(message="password cant be left blank")
	private String password;

}
