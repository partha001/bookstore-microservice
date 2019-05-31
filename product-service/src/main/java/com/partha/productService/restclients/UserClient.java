package com.partha.productService.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.partha.productService.dto.User;



@FeignClient("userService")
public interface UserClient {
	
	@GetMapping(value="/test")
	public String test();
	
	@GetMapping(value="/users/{id}")
	public  ResponseEntity<User> findWithId(@PathVariable Integer id);
	
	@GetMapping(value="/users/username/{username}")
	public  ResponseEntity<User> findWithUsername(@PathVariable("username") String  username);

}
