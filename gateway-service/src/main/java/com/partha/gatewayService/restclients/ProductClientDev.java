package com.partha.gatewayService.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Profile(value="dev")
@FeignClient(name="productService", url="product-service:30085")
public interface ProductClientDev extends ProductClient{
	
//	@GetMapping(value = "/books")
//	public ResponseEntity<Object>  getAllBooks();

}
