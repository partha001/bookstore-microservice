package com.partha.userService.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.partha.userService.entities.User;
import com.partha.userService.service.UserService;

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/users/{id}")
	public  ResponseEntity<User> findWithId(@PathVariable Integer id){
		logger.info("UserController.findWithId() :: start");
		return new ResponseEntity<>(userService.findWithId(id), HttpStatus.OK);	
	}
	
	
	@GetMapping(value="/users/username/{username}")
	public  ResponseEntity<User> findWithUsername(@PathVariable("username") String  username){
		   logger.info("UserController.findWithUsername() :: start");
			return new ResponseEntity<>(userService.findWithUsername(username), HttpStatus.OK);		
	}
	
	
	
	@PostMapping(value="/users/register")
	public ResponseEntity<User> register(@RequestBody User user){
		logger.info("UserController.register() :: start");
		return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
	}
	
	

}
