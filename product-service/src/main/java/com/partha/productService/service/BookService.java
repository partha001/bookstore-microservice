package com.partha.productService.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partha.productService.builder.BookDtoBuilder;
import com.partha.productService.dto.BookDto;
import com.partha.productService.entities.Book;
import com.partha.productService.model.SearchBook;
import com.partha.productService.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	//methods that correspond to controllers
	public List<BookDto> getAllBooks(){
		List<Book> books = ((List<Book>)repository.findAll());
		List<BookDto> bookList =books.stream()
				.filter(book -> book.getActive())
				.map(book -> BookDtoBuilder.bookDto(book))
				.collect(Collectors.toList());
		return bookList;
	}
	
	public BookDto getBookById(int id){
		Optional<Book> result = repository.findById(id);getClass();
		BookDto dto = null;
		if( result.isPresent()){
			Book book= result.get();
			dto =	BookDto.builder()
					.id(book.getId())
					.title(book.getTitle())
					.author(book.getAuthor())
					.category(book.getCategory())
					.publisher(book.getPublisher())
					.language(book.getLanguage())
					.pages(book.getPages()==null ? 0 : book.getPages())
					.format(book.getFormat())
					.isbn(book.getIsbn())
					.description(book.getDescription())
					.availableUnits(book.getAvailableUnits())
					.price(book.getPrice())
					.image( Base64.getEncoder().encodeToString(book.getImage()))
					//.publicationDate(book.getPublicationDate())
					.status(book.getActive()?"Active":"Inactive")
					.build();
		}
		return dto;		
	}
	
	@Transactional
	public List<BookDto> searchBook(SearchBook search){
		List<Book> books = repository.searchBooks(search.getItemsPerPage(), search.getCurrentPage() );
		List<BookDto> result =books.stream()
							.map(book -> BookDtoBuilder.bookDto(book))
							.collect(Collectors.toList());
		return result;
	}

	public int getPageCount(int itemsPerPage) {
		int bookCount = repository.searchBooksCount();
		int pageCount = 0;
		if(bookCount < itemsPerPage)
			pageCount = 1;
		if(bookCount % itemsPerPage == 0)
			pageCount = bookCount/itemsPerPage;
		else 
			pageCount = (bookCount/itemsPerPage) + 1;	
		return pageCount;		
	}

}
