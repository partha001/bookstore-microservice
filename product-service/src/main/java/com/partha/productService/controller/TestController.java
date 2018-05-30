package com.partha.productService.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Value("${test.key1}")
	private String key1;
	
	@GetMapping(value="/test")
	public String test(){
		return  key1;
	}

}
