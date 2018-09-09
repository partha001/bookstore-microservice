package com.partha.productService.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.partha.productService.entities.Book;
import com.partha.productService.repositories.BookRepository;

public class ProductServiceTest {
	
	@Mock
	BookRepository bookRepository;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}


	
		
	//reusable methods which correspond to repository start	
	@Test
	public void findAllBooksShouldReturnBooks(){
		ProductService service= new ProductService();
		service.setBookRepository(bookRepository);
		
		//stubbing mocking data
		List<Book> expectedResult=new ArrayList<>();
		expectedResult.add(new Book());
		expectedResult.add(new Book());
		when(bookRepository.findAll()).thenReturn(expectedResult);
		
		List<Book> actualResult= service.findAllBooks();
		
		//verifying
		assertEquals(expectedResult.size(), actualResult.size());
		verify(bookRepository,times(1)).findAll();
	}

	@Test
	public void createBookShouldCreateBook() {
		ProductService service= new ProductService();
		service.setBookRepository(bookRepository);
		
		Book book = new Book();
		when(bookRepository.save(book)).thenReturn(book);
		Book actualResult= service.createBook(book);
		
		assertEquals(book, actualResult);
		verify(bookRepository,times(1)).save(book);
	}
	//reusable methods which correspond to repository end
	
	
	//methods with correspond to controllers start
	
	
	
	//methods with correspond to controllers end

}
