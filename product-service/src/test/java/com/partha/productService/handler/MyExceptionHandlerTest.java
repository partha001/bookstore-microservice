package com.partha.productService.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.partha.productService.exception.MyException;
import com.partha.productService.response.MyExceptionResponse;

public class MyExceptionHandlerTest {
	
//	@ExceptionHandler(MyException.class)
//	public final ResponseEntity<Object> handleMyException(MyException ex, WebRequest request){
//		MyExceptionResponse response=new MyExceptionResponse(ex);
//		return new ResponseEntity<>(response,response.getHttpStatusCode());
//	}

	@Test
	public void testHandleMyExceptionShouldProductResponseEntityWhenNoStackTrace() {
		MyExceptionHandler handler= new MyExceptionHandler();
		
		MyException ex= new MyException(HttpStatus.BAD_GATEWAY, "it is a bad request", new RuntimeException());
		//ex.setStackTrace(stackTrace);
		MyExceptionResponse response=new MyExceptionResponse(ex);
		ResponseEntity<Object> result = handler.handleMyException(ex, null);
		assertEquals(HttpStatus.BAD_GATEWAY, result.getStatusCode());
		assertEquals("it is a bad request", ((MyExceptionResponse)result.getBody()).getMessage());
	}
	
	@Test
	public void testHandleMyExceptionShouldProductResponseEntityWhenThereIsStackTrace() {
		MyExceptionHandler handler= new MyExceptionHandler();
		MyException ex=null;
		try{
			int i=10/0;
		}catch(Exception exception){
			ex= new MyException(HttpStatus.BAD_GATEWAY, "it is a bad request", exception);
		}
		//MyException ex= new MyException(HttpStatus.BAD_GATEWAY, "it is a bad request", new RuntimeException());
		//ex.setStackTrace(stackTrace);
		MyExceptionResponse response=new MyExceptionResponse(ex);
		ResponseEntity<Object> result = handler.handleMyException(ex, null);
		assertEquals(HttpStatus.BAD_GATEWAY, result.getStatusCode());
		assertEquals("it is a bad request", ((MyExceptionResponse)result.getBody()).getMessage());
	}

}
