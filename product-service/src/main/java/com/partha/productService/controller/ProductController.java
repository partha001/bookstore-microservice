package com.partha.productService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partha.productService.entities.Book;
import com.partha.productService.service.BookService;

@RestController
public class ProductController {
	
	@Autowired
	private BookService service;
	
	@GetMapping(value="/books/")
	public ResponseEntity<List<Book>> getAllBooks(){
		return new ResponseEntity<List<Book>>(service.getAllBooks(),HttpStatus.OK);
	}
}
