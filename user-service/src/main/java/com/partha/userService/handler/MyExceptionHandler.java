package com.partha.userService.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.partha.userService.dto.MyExceptionDto;
import com.partha.userService.exception.MyException;

@RestController
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler{
	
	public static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);
	
	@ExceptionHandler(MyException.class)
	public final ResponseEntity<MyExceptionDto> handleMyException(MyException ex){
		logger.error("MyExceptionHandler.handleMyException() :: error",ex);
		MyExceptionDto dto = MyExceptionDto.builder()
				.errorMessage(ex.getMessage())
				.stackTrace(ex.getException()==null? null : ex.getException().getMessage())
				.build();
		return new ResponseEntity<MyExceptionDto>(dto, ex.getStatusCode());
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<MyExceptionDto> handleException(MyException ex){
		logger.error("MyExceptionHandler.handleException() :: error",ex);
		MyExceptionDto dto = MyExceptionDto.builder()
				.errorMessage(ex.getMessage())
				.stackTrace(ex.getException()==null? null : ex.getException().getMessage())
				.build();
		return new ResponseEntity<MyExceptionDto>(dto, ex.getStatusCode());
	}
	

}

