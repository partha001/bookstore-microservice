package com.partha.productService.repositories;

import java.util.List;

import com.partha.productService.entities.Book;

public interface CustomBookRepository {

	public List<Book> searchBooks(int itemsPerPage,int pageNo );

	public int searchBooksCount();

}
