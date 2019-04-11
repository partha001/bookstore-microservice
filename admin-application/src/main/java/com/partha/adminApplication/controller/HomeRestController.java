package com.partha.adminApplication.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {
	
	@GetMapping(value="/test1")
	public ResponseEntity<String> test1(){
		JSONObject obj = new JSONObject();
		try {
			obj.put("key1", "value1");
			obj.put("key2", "value2");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
		
	}

}
