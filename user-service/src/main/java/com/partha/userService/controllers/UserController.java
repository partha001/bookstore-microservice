package com.partha.userService.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.partha.userService.entities.User;
import com.partha.userService.exception.MyException;
import com.partha.userService.service.UserService;

import springfox.documentation.swagger.readers.operation.ResponseHeaders;

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/users/{id}")
	public  User findById(@PathVariable Integer id){
		throw new MyException(HttpStatus.INTERNAL_SERVER_ERROR, "not a valid id", new RuntimeException());
	}
	
	
	@GetMapping(value="/users/username/{username}")
	public  User findByUsername(@PathVariable("username") String  username){
		return userService.findByUsername(username);
	}
	
	
	@PostMapping(value="/users/login")
	public  ResponseEntity<User> login(@RequestBody User user){
		logger.info("UserController.login() :: start");
		User foundUser=userService.login(user);
		String authenticationToken=userService.generateToken(foundUser);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json; charset=UTF-8");
	    headers.add("auth-token", authenticationToken);
		logger.info("UserController.login() :: end");
		return new ResponseEntity<User>(foundUser, headers, HttpStatus.OK);
	}
	
	
	
	
	

}
