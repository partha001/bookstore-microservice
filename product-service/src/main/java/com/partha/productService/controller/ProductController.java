package com.partha.productService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.partha.productService.entities.Book;
import com.partha.productService.service.ProductService;

@RestController
public class ProductController {
	
	private static final Logger logger= LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value="/test1")
	public ResponseEntity<Object> test1(){
		logger.info("inside ProductController.test1()");
		return new ResponseEntity<Object>("Success", HttpStatus.OK);
	}
	
	@GetMapping(value = "/books")
	public ResponseEntity<Object>  getAllBooks() {
		logger.info("inside ProductController.getAllBooks()");
		return new ResponseEntity<Object>(service.findAllBooks(), HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/books")
	public ResponseEntity<Object> createBook(@RequestBody Book book) {
		logger.info("inside ProductController.createBook()");
		service.createBook(book);
		return new ResponseEntity<Object>("book added successfully", HttpStatus.OK);
	}

}
