package com.partha.gatewayService.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controller01 {
	
	
	@GetMapping(value="/login")
	public String mylogin(){
		return "login_page";
	}
	
	
	@GetMapping(value="/home1")
	public String home1(){
		return "home_page";
	}
	
	@ResponseBody
	@GetMapping(value="/home")
	public Principal home(Principal principal){
		return principal;
	}

}
