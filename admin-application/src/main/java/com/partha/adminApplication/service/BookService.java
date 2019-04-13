package com.partha.adminApplication.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partha.adminApplication.builder.BookDtoBuilder;
import com.partha.adminApplication.dto.BookDto;
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
						.enabled(true)
						.build();
		return repository.save(book);
	}
	
	@Transactional
	public void deleteBook(int id){
		repository.deleteById(id);
	}
		
	@Transactional
	public List<BookDto> showBooks(){
		List<Book> books = ((List<Book>)repository.findAll());
	    List<BookDto> bookList =books.stream()
				.map(book -> BookDtoBuilder.bookDto(book))
				.collect(Collectors.toList());
		return bookList	;			
	}
	
}