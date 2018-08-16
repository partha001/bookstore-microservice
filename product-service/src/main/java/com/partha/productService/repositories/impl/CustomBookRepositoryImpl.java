package com.partha.productService.repositories.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.partha.productService.entities.Book;
import com.partha.productService.repositories.CustomBookRepository;

public class CustomBookRepositoryImpl implements CustomBookRepository{
	
	@Autowired
	private MongoTemplate template;

	private static final Logger logger = LoggerFactory.getLogger(CustomBookRepositoryImpl.class);

	@Override
	public List<Book> getMicroserviceJavaBooks() {
		//have my own business logic
		return null;
	}
	
	

}
