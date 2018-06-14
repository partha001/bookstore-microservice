package com.partha.userService.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class MyException extends RuntimeException{
		

	private Date timestamp;
	private String message;
	private HttpStatus httpStatusCode;	
	private Exception exception;

	public MyException(HttpStatus httpStatusCode, String message, Exception exception) {
		super();
				
		this.timestamp = new Date();
		this.message = message;
		this.httpStatusCode = httpStatusCode;
		this.exception = exception;
	}

	public Date getTimestamp() {
		return timestamp;
	}


	public String getMessage() {
		return message;
	}


	public HttpStatus getHttpStatusCode() {
		return httpStatusCode;
	}	
	
	public Exception getException() {
		return exception;
	}	
	

}
