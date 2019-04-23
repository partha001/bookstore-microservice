package com.partha.databaseService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller1 {
	
	@GetMapping(value="/check")
	public ResponseEntity<Object> check(){
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
