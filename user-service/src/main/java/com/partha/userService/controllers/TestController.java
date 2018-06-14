package com.partha.userService.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.partha.userService.entities.User;
import com.partha.userService.repositories.UserRepository;
import com.partha.userService.restclients.ProductClient;

@RestController
@RefreshScope
public class TestController {
	
	private static final Logger logger =LoggerFactory.getLogger(TestController.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductClient productClient;



	@Value("${key1}")
	private String testKey;
	
	
	@GetMapping(value="/test4")
	public String test4(){
		logger.info("TestController.test4() :: start");
		System.out.println(testKey);
		return productClient.test();
	}


	@GetMapping(value="/test1")
	public String test1(){
		System.out.println(testKey);
		return testKey;
	}



	@GetMapping(value="/test")
	public User test(){
		Optional<User> foundUser=userRepository.findById(1);
		if(foundUser.isPresent()){
			System.out.println(foundUser.get());
			return foundUser.get();
		}else{
			System.out.println("user not found");
			return null;
		}
	}


	@PostMapping(value="/test2")
	public User test2(@RequestBody User user){
//		user.setUsername("partha");
//	//		User user=new User();
//	user.setPassword("partha");
//		user.setAccountNonExpired(false);
//		user.setAccountNonLocked(false);
//		user.setCredentialsNonExpired(false);
//		user.setEnabled(true);
		User savedUser = userRepository.save(user);
		return savedUser;
	}




}
