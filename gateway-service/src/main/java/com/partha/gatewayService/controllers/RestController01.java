package com.partha.gatewayService.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partha.gatewayService.model.Partner;

@RestController
public class RestController01 {
	
	public static final Logger logger= LoggerFactory.getLogger(RestController01.class);
	
	
	@GetMapping(value="/test1")
	public String test1(){
		logger.info("inside RestController01.test1()");
		return "test1";
	}

	
	@GetMapping(value="/test2")
	public String test2(){
		logger.info("inside RestController01.test2()");
		return "test2";
	}
	
	//spring security related endpoints start
	@GetMapping(value="/entrypoint")
	public ResponseEntity<Object> entrypoint(){
		logger.info("inside RestController01.entrypoint()");
		return new ResponseEntity<Object>("Unauthorized request",HttpStatus.UNAUTHORIZED);
	}
	
	
	@GetMapping(value="/authenticationFailed")
	public ResponseEntity<Object> authenticationFailed(){
		logger.info("inside RestController01.authenticationFailed()");
		return new ResponseEntity<Object>("Username or password incorrect",HttpStatus.BAD_REQUEST);
	}
	//spring security related endpoints end
	
	
	@GetMapping(value="/partners")
	public ResponseEntity<Object> partners(){
		logger.info("inside RestController01.partners()");
		List<Partner> partners= new ArrayList<>();
		partners.add(new Partner(1, "state bank of india", "banking", "we provide banking solutions to them and they have been trusted partner over years"));
		partners.add(new Partner(2, "apollo", "hospitality", "we provide healthcare solutions to them and they have been trusted partner over years"));
		partners.add(new Partner(3, "big bazzar", "retail", "we provide retail solutions to them and they have been trusted partner over years"));
		return new ResponseEntity<Object>(partners,HttpStatus.OK);
	}
	
}
