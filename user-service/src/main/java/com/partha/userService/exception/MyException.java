package com.partha.userService.exception;

import org.springframework.http.HttpStatus;

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
public final class MyException extends RuntimeException{
		

	private String message;
	private Exception exception;
	private HttpStatus statusCode;

}
