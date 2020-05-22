package com.partha.adminApplication.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.partha.adminApplication.entities.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>,CustomBookRepository{

}
