package com.partha.productService.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.partha.productService.entities.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>, CustomBookRepository {

}
