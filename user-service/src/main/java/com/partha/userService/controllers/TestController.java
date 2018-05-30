package com.partha.userService.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partha.userService.entities.User;
import com.partha.userService.repositories.UserRepository;

@RestController
public class TestController {
	
	@Autowired
	private UserRepository repository;
	
	
   @GetMapping(value="/test")
   public User test(){
	   Optional<User> foundUser=repository.findById(1);
	   if(foundUser.isPresent()){
		   System.out.println(foundUser.get());
		   return foundUser.get();
	   }else{
		   System.out.println("user not found");
		   return null;
	   }
   }
   

}
