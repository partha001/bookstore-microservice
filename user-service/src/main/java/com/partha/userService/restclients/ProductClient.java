package com.partha.userService.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("productService")
public interface ProductClient {
	
	@GetMapping(value="/test")
	public String test();

}
