package com.partha.productService.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.partha.productService.dto.MyExceptionDto;
import com.partha.productService.exception.MyException;

@RestController
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler{
	

	
	@ExceptionHandler(MyException.class)
	public final ResponseEntity<MyExceptionDto> handleMyException(MyException ex){
		MyExceptionDto dto = MyExceptionDto.builder()
				.errorMessage(ex.getMessage())
				.stackTrace(ex.getException()==null? null : ex.getException().getMessage())
				.build();
		return new ResponseEntity<MyExceptionDto>(dto, ex.getStatusCode());
	}

}

