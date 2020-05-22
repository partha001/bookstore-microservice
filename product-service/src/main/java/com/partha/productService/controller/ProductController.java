package com.partha.productService.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.partha.productService.dto.AddToCartDto;
import com.partha.productService.dto.BookDto;
import com.partha.productService.dto.CartItemsDto;
import com.partha.productService.model.CartModel;
import com.partha.productService.model.SearchBook;
import com.partha.productService.response.SuccessResponse;
import com.partha.productService.service.BookService;

@RestController
public class ProductController {
	
	@Autowired
	private BookService service;
	
	@GetMapping(value="/books")
	public ResponseEntity<List<BookDto>> getAllBooks(){
		return new ResponseEntity<List<BookDto>>(service.getAllBooks(),HttpStatus.OK);
	}
	
	@GetMapping(value="/books/{id}")
	public ResponseEntity<BookDto> getBookById(@PathVariable int id){
		BookDto result = service.getBookById(id);
		if (result != null)
			return new ResponseEntity<BookDto>(result,HttpStatus.OK);
		else
			return new ResponseEntity<BookDto>(result,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/searchBook")
	public ResponseEntity<List<BookDto>> searchBook(@RequestBody SearchBook search){
		List<BookDto> booklist = new ArrayList<BookDto>();
		booklist = service.searchBook(search);
		return new ResponseEntity<List<BookDto>>(booklist ,HttpStatus.OK );
	}
	
	@GetMapping(value="/books/pageCount")
	public ResponseEntity<List<Integer>> getPageCount(@RequestParam(value = "itemsPerPage") int itemsPerPage){
		Integer count = service.getPageCount(itemsPerPage);
		List<Integer> list =IntStream.range(1, count+1)
									.boxed()
									.collect(Collectors.toList());
		return new ResponseEntity<List<Integer>>(list,HttpStatus.OK);		
	}
	
	@PostMapping(value="/cartItems")
	public ResponseEntity<AddToCartDto> addToCart(@RequestBody CartModel cartModel){
		return new ResponseEntity<AddToCartDto>(service.addToCart(cartModel),HttpStatus.CREATED);
	}
	
	@GetMapping(value="/cartItems/{userId}")
	public ResponseEntity<List<CartItemsDto>> getCartItemsByUserId(@PathVariable(value="userId") Integer userId){
		return new ResponseEntity<List<CartItemsDto>>(service.getCartItems(userId),HttpStatus.OK);
	}
	
	@DeleteMapping(value="/cartItems/{cartId}")
	public ResponseEntity<?> deleteItem(@PathVariable(value="cartId") Integer cartId){
		service.delete(cartId);
		SuccessResponse response = SuccessResponse.builder()
				.message("deletion Completed successfully")
				.build();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	
	@PutMapping(value="/cartItems/{cartId}")
	public ResponseEntity<?> updateCartItem(@PathVariable(value="cartId") Integer cartId,@RequestParam(value="quantity") Integer quantity){
		service.updateCartItem(cartId,quantity);
		SuccessResponse response = SuccessResponse.builder()
			.message("updated completed successfully")
			.build();
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	@PostMapping(value="/placeOrder/{userId}")
	public ResponseEntity<?> placeOrder(@PathVariable(value="userId") Integer userId){
		service.placeOrder(userId);
		SuccessResponse response = SuccessResponse.builder()
				.message("order placed successfully")
				.build();
			return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/orderHistory/{userId}")
	public ResponseEntity<?> getOrderHistory(@PathVariable(value="userId") Integer userId){
		return service.getOrderHistory(userId);
	}
		
}
