package com.partha.adminApplication.builder;

import com.partha.adminApplication.dto.BookDto;
import com.partha.adminApplication.entities.Book;

public class BookDtoBuilder {
	
	public static BookDto bookDto(Book book){
		return BookDto.builder()
			.id(book.getId())
			.title(book.getTitle())
			.author(book.getAuthor())
			.category(book.getCategory())
			.availableUnits(book.getAvailableUnits())
			.price(book.getPrice())
			.status(book.getEnabled()? "Active": "Inactive")
			.build();
	}

}
