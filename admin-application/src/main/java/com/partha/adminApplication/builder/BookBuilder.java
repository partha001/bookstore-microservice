package com.partha.adminApplication.builder;

import java.io.IOException;
import java.util.Date;

import com.partha.adminApplication.entities.Book;
import com.partha.adminApplication.model.BookModel;

public class BookBuilder {
	
	public static Book build(Book book, BookModel model) throws IOException{
		Date currdate = new Date();
		
		book.setTitle(model.getBookName());
		book.setAuthor(model.getAuthor());
		book.setCategory(model.getCategory());
		book.setAvailableUnits(model.getAvailableUnits());
		book.setPrice(model.getPrice());
		book.setActive(model.isActive());
		book.setPublisher(model.getPublisher());
		book.setLanguage(model.getLanguage());
		book.setPages(model.getPages());
		book.setIsbn(model.getIsbn());
		book.setFormat(model.getFormat());
		book.setDescription(model.getDescription());
		//book.setPublicationDate(model.getPublicationDate());
		book.setUpdateDate(currdate);
		book.setImage(model.getBookImage()!=null?model.getBookImage().getBytes():null);
		
		return book;		
	}

}
