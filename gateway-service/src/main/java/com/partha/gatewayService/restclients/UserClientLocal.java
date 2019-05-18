package com.partha.gatewayService.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.partha.gatewayService.bo.User;

@Profile(value="local")
@FeignClient(name="userService")
public interface UserClientLocal extends UserClient {
	
//	@GetMapping(value="/users/username/{username}")
//	public  User findByUsername(@PathVariable("username") String  username);

}
