package com.partha.productService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	private Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Value("${test.key1}")
	private String key1;
	
	@GetMapping(value="/test")
	public String test(){
		logger.info("TestController.test() :: start"); 
		return  key1;
	}

}
