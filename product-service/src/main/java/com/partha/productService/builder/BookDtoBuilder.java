package com.partha.productService.builder;

import java.util.Base64;

import com.partha.productService.dto.BookDto;
import com.partha.productService.entities.Book;


public class BookDtoBuilder {
	
	public static BookDto bookDto(Book book){
		return BookDto.builder()
			.id(book.getBookId())
			.title(book.getTitle())
			.author(book.getAuthor())
			.category(book.getCategory())
			.availableUnits(book.getAvailableUnits())
			.price(book.getPrice())
			.description(book.getDescription())
			.publisher(book.getPublisher())
			.image(Base64.getEncoder().encodeToString(book.getImage()))
			.status(book.getActive()? "Active": "Inactive")
			.build();
	}

}
