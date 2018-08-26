package com.partha.gatewayService.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient("productService")
public interface ProductClient {
	
	@GetMapping(value = "/books")
	public ResponseEntity<Object>  getAllBooks();

}
