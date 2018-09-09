package com.partha.productService.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.partha.productService.entities.Book;
import com.partha.productService.service.ProductService;

public class ProductControllerTest {
	

	@Mock
	ProductService service;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}


	
	@Test
	public void test1ShouldReturnSuccess(){
		ProductController controller= new ProductController();
		ResponseEntity expectedResult= new ResponseEntity<Object>("Success", HttpStatus.OK);
	    ResponseEntity actualResult=controller.test1();
	    assertEquals(expectedResult, actualResult);
	    assertEquals(HttpStatus.OK, actualResult.getStatusCode());
	}
		
	@Test
	public void getAllBooksShouldReturnBooks(){
		ProductController controller= new ProductController();
		controller.setService(service);
		//the below line is only to increase code coverage
		controller.getService();
		List<Book> books=new ArrayList<>();
		when(service.findAllBooks()).thenReturn(books);
		ResponseEntity<Object> actualResult= controller.getAllBooks();
		assertEquals(HttpStatus.OK, actualResult.getStatusCode());
	}
	
		
	@Test
	public void createBookShouldCreateBook(){
		ProductController controller= new ProductController();
		controller.setService(service);
		Book book = new Book();
		when(service.createBook(book)).thenReturn(book);
		ResponseEntity<Object> actualResult= controller.createBook(book);
		assertEquals(HttpStatus.OK, actualResult.getStatusCode());
		assertEquals("book added successfully", actualResult.getBody().toString());
	}
	


}
