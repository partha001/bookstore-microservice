package com.partha.gatewayService.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestController01 {
	
	
	@GetMapping(value="/test1")
	public String test1(){
		return "test1";
	}

	
	@GetMapping(value="/test2")
	public String test2(){
		return "test2";
	}
	
	
	@GetMapping(value="/entrypoint")
	public ResponseEntity<Object> entrypoint(){
		return new ResponseEntity<Object>("Authentication failed",HttpStatus.UNAUTHORIZED);
	}
	
	
	@GetMapping(value="/authenticationFailed")
	public ResponseEntity<Object> authenticationFailed(){
		return new ResponseEntity<Object>("Username or password incorrect",HttpStatus.BAD_REQUEST);
	}
	
}
