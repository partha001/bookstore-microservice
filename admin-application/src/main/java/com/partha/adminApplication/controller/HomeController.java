package com.partha.adminApplication.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index(){
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String home(){
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
//	@RequestMapping("/test")
//	public String test(HttpServletRequest req){
//		//System.out.println(req.getServletContext().getRealPath("/"));
//		//System.out.println(req.getContextPath());
//		return "test";
//	}
	
	
}
