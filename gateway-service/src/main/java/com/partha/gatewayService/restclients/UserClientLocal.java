package com.partha.gatewayService.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile(value="local")
@FeignClient(name="userService")
public interface UserClientLocal extends UserClient {
	

}
