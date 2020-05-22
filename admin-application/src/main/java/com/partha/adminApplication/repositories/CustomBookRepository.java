package com.partha.adminApplication.repositories;

import java.util.List;

import com.partha.adminApplication.entities.Book;

public interface CustomBookRepository {

	public List<Book> searchBooks(int itemsPerPage,int pageNo );

	public int searchBooksCount();

}
