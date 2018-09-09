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
	
	private static final Logger LOGGER= LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	//reusable methods which correspond to repository start
	public List<Book> findAllBooks(){
		LOGGER.info("inside ProductService.findAllBooks()");
		return bookRepository.findAll();
	}

	public Book createBook(Book book) {
		LOGGER.info("inside ProductService.createBook()");
		return bookRepository.save(book);
	}
	//reusable methods which correspond to repository end
	
	
	//methods with correspond to controllers start
	
	
	
	//methods with correspond to controllers end
	
	
	
	//getters and setters start
	public BookRepository getBookRepository() {
		return bookRepository;
	}

	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}	
	//getters and setters end
	
	

}
