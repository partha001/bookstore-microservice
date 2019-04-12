package com.partha.adminApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partha.adminApplication.entities.Book;
import com.partha.adminApplication.model.BookModel;
import com.partha.adminApplication.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	@Transactional
	public Book addBook(BookModel model) throws Exception{
		Book book = Book.builder()
						.title(model.getBookName())
						.author(model.getAuthor())
						.category(model.getCategory())
						.availableUnits(model.getAvailableUnits())
						.price(model.getPrice())
						.build();
		return repository.save(book);
	}

}
