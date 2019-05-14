package com.partha.databaseService.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.partha.databaseService.entities.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{

}
