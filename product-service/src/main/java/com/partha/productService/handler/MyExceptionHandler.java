package com.partha.productService.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.partha.productService.exception.MyException;
import com.partha.productService.response.MyExceptionResponse;

@RestController
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler{
	
//	@ExceptionHandler(Exception.class)
//	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){		
//		MyException exception=new MyException(HttpStatus.INTERNAL_SERVER_ERROR, "", ex);
//		MyExceptionResponse response=new MyExceptionResponse(exception);
//		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR );
//	}
	
	@ExceptionHandler(MyException.class)
	public final ResponseEntity<Object> handleMyException(MyException ex, WebRequest request){
		MyExceptionResponse response=new MyExceptionResponse(ex);
		return new ResponseEntity<>(response,response.getHttpStatusCode());
	}

}

