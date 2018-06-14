package com.partha.gatewayService.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.partha.gatewayService.dto.User;


@FeignClient("userService")
public interface UserClient {
	
	@GetMapping(value="/users/username/{username}")
	public  User findByUsername(@PathVariable("username") String  username);

}
