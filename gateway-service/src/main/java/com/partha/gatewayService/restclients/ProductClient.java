package com.partha.gatewayService.restclients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


public interface ProductClient {
	
	@GetMapping(value = "/books")
	public ResponseEntity<Object>  getAllBooks();

}
