package com.partha.productService.response;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.partha.productService.exception.MyException;

public class MyExceptionResponse {

	private Date timestamp;
	private String message;
	private String errorMessage;
	private String stackTrace;
	
	@JsonIgnore
	private HttpStatus httpStatusCode;	



	public MyExceptionResponse(MyException ex) {
		super();

		this.timestamp = ex.getTimestamp();
		this.message = ex.getMessage();
		this.httpStatusCode = ex.getHttpStatusCode();
		this.errorMessage = (ex.getException()!=null ? "" : ex.getException().getMessage());	
		this.stackTrace   = (ex.getException()!=null ? "" : ex.getStackTrace().toString());
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getStackTrace() {
		return stackTrace;
	}


}
