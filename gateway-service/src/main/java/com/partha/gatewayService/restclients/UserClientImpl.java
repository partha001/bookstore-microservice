package com.partha.gatewayService.restclients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.partha.gatewayService.bo.User;
import com.partha.gatewayService.restclients.UserClient;

@Component
//@Profile(value="dev")
//@FeignClient(name="userService",url="user-service:30086")
public class UserClientImpl implements UserClient {
	
	public static final Logger logger = LoggerFactory.getLogger(UserClientImpl.class);
		
	@Value("${zuul.routes.userService.url}")
	String baseurl;
	

	@Override
	public User findByUsername(String username) {
		logger.debug("inside UserClientDevImpl.findByUsername()");
	    RestTemplate restTemplate = new RestTemplate();
	    String url = baseurl + "/users/findByUsername/"+username;
	    logger.info("pinging url :"+ url);
	    User user = restTemplate.getForObject(url, User.class);
	    return user;
	}

}
