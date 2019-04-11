package com.partha.adminApplication.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@NotBlank(message="username cant be left blank")
	//@NotNull(message="username cant be left blank")
	//@Size(min=2, max=30 ,message="username should be between 2 and 30 characters")
	private String username;
	
	//@NotNull(message="password cant be left blank")
	@NotBlank(message="password cant be left blank")
	//@Size(min=2, max=30 , message="password should be at least 2 characters")
	private String password;

}
