package com.partha.gatewayService.restclients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.partha.gatewayService.restclients.ProductClient;

@Component
//@Profile(value="dev")
public class ProductClientImpl implements ProductClient {
	
	public static final Logger logger = LoggerFactory.getLogger(ProductClientImpl.class);
		
	@Value("${restclient.productService.baseurl}")
	String baseurl;

	@Override
	public ResponseEntity<Object> getAllBooks() {
		logger.info("inside ProductClientDevImpl.getAllBooks()");
		return null;
	}
	

}
