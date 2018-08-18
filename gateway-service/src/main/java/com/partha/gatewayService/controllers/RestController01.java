package com.partha.gatewayService.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.partha.gatewayService.model.OrganizationalUpdate;
import com.partha.gatewayService.model.Partner;
import com.partha.gatewayService.restclients.UserClient;




@RestController
public class RestController01 {
	
	public static final Logger logger= LoggerFactory.getLogger(RestController01.class);
	
	@Autowired
	private UserClient userClient;
	
	@GetMapping(value="/test1")
	public String test1(){
		logger.info("inside RestController01.test1()");
		return "test1";
	}
	
	@GetMapping(value="/test2")
	public com.partha.gatewayService.bo.User test2(){
		logger.info("inside RestController01.test2()");
		com.partha.gatewayService.bo.User user=userClient.findByUsername("partha@gmail.com");
		return user;
	}
	

	
	
	@PostMapping(value="/test3")
	public String test3(@RequestBody String request){
		logger.info("inside RestController01.test3() :: request_payload :"+request);
		System.out.println(request);
		return request;
	}
	
	//spring security related endpoints start
	@GetMapping(value="/entrypoint")
	public ResponseEntity<Object> entrypoint(){
		logger.info("inside RestController01.entrypoint()");
		return new ResponseEntity<Object>("Unauthorized request",HttpStatus.UNAUTHORIZED);
	}
	
	
	@GetMapping(value="/authenticationFailed",produces=MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Object> authenticationFailed(){
		logger.info("inside RestController01.authenticationFailed()");
		JSONObject obj=new JSONObject();
		try {
			obj.put("reponse_message", "Username or password incorrect");
		} catch (JSONException e) {
			logger.error("RestController01.authenticationFailed() :: error",e);
		}
		return new ResponseEntity<Object>(obj,HttpStatus.BAD_REQUEST);
//		return new ResponseEntity<Object>("Username or password incorrect",HttpStatus.BAD_REQUEST);
	}
	//spring security related endpoints end
	
	
	@GetMapping(value="/partners")
	public ResponseEntity<Object> partners(){
		logger.info("inside RestController01.partners()");
		List<Partner> partners= new ArrayList<>();
		partners.add(new Partner(1, "O’Reilly books", "programming", "we have been one of the topmost selling platforms for OReilly publications"));
		partners.add(new Partner(2, "Head First", "mixed bag", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. "));
		partners.add(new Partner(3, "Mc GRAWHILL", "technology", "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"));
		return new ResponseEntity<Object>(partners,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/updates")
	public ResponseEntity<Object> organizationalUpdates(){
		logger.info("inside RestController01.organizationalUpdates()");
		List<OrganizationalUpdate> updates= new ArrayList<>();
		updates.add(new OrganizationalUpdate("IndependenceDay Offer !", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. "));
		updates.add(new OrganizationalUpdate("Special Discounts", "The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
		updates.add(new OrganizationalUpdate("From CEO's desk!", "The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."));
		//updates.add(new OrganizationalUpdate(updateHeading, updateDesc));
		return new ResponseEntity<Object>(updates,HttpStatus.OK);
	}
	
	
}
