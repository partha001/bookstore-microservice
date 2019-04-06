package com.partha.productService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partha.productService.entities.Book;
import com.partha.productService.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	//methods that correspond to controllers
	public List<Book> getAllBooks(){
		return (List<Book>)repository.findAll();
	}
	
	
	

}
