package com.partha.gatewayService.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controller01 {
	
	public static final Logger logger = LoggerFactory.getLogger(Controller01.class);
	
	
//	@GetMapping(value="/login")
//	public String mylogin(){
//		logger.info("inside Controller01.mylogin()");
//		return "login_page";
//	}
	
	
	@GetMapping(value="/home1")
	public String home1(){
		logger.info("inside Controller01.home1()");
		return "home_page";
	}
	
	@ResponseBody
	@GetMapping(value="/home") 
	public Principal home(Principal principal){
		logger.info("inside Controller01.home()");
		System.out.println("inside Controller01.home()");
		return principal;
	}

}
