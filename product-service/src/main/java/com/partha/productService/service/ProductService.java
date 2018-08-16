package com.partha.productService.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partha.productService.entities.Book;
import com.partha.productService.repositories.BookRepository;

@Service
public class ProductService {
	
	private static final Logger logger= LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	//reusable methods which correspond to repository start
	public List<Book> findAllBooks(){
		logger.info("inside ProductService.findAllBooks()");
		return bookRepository.findAll();
	}

	public void createBook(Book book) {
		logger.info("inside ProductService.createBook()");
		bookRepository.save(book);
	}
	//reusable methods which correspond to repository end
	
	
	//methods with correspond to controllers start
	
	
	
	//methods with correspond to controllers end

}
