package com.partha.productService.service;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partha.productService.builder.BookDtoBuilder;
import com.partha.productService.dto.AddToCartDto;
import com.partha.productService.dto.BookDto;
import com.partha.productService.dto.CartItemsDto;
import com.partha.productService.entities.Book;
import com.partha.productService.entities.CartItem;
import com.partha.productService.model.CartModel;
import com.partha.productService.model.SearchBook;
import com.partha.productService.repositories.BookRepository;
//import com.partha.productService.repositories.CartItemRepository;
import com.partha.productService.repositories.CartRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CartRepository	cartRepository;
	
//	@Autowired
//	private CartItemRepository cartItemRepository;
	
	//methods that correspond to controllers
	public List<BookDto> getAllBooks(){
		List<Book> books = ((List<Book>)repository.findAll());
		List<BookDto> bookList =books.stream()
				.filter(book -> book.getActive())
				.map(book -> BookDtoBuilder.bookDto(book))
				.collect(Collectors.toList());
		return bookList;
	}
	
	public BookDto getBookById(int id){
		Optional<Book> result = repository.findById(id);
		BookDto dto = null;
		if( result.isPresent()){
			Book book= result.get();
			dto =	BookDto.builder()
					.id(book.getBookId())
					.title(book.getTitle())
					.author(book.getAuthor())
					.category(book.getCategory())
					.publisher(book.getPublisher())
					.language(book.getLanguage())
					.pages(book.getPages()==null ? 0 : book.getPages())
					.format(book.getFormat())
					.isbn(book.getIsbn())
					.description(book.getDescription())
					.availableUnits(book.getAvailableUnits())
					.price(book.getPrice())
					.image( Base64.getEncoder().encodeToString(book.getImage()))
					//.publicationDate(book.getPublicationDate())
					.status(book.getActive()?"Active":"Inactive")
					.build();
		}
		return dto;		
	}
	
	@Transactional
	public List<BookDto> searchBook(SearchBook search){
		List<Book> books = repository.searchBooks(search.getItemsPerPage(), search.getCurrentPage() );
		List<BookDto> result =books.stream()
							.map(book -> BookDtoBuilder.bookDto(book))
							.collect(Collectors.toList());
		return result;
	}

	public int getPageCount(int itemsPerPage) {
		int bookCount = repository.searchBooksCount();
		int pageCount = 0;
		if(bookCount < itemsPerPage)
			pageCount = 1;
		if(bookCount % itemsPerPage == 0)
			pageCount = bookCount/itemsPerPage;
		else 
			pageCount = (bookCount/itemsPerPage) + 1;	
		return pageCount;		
	}

	@Transactional
	public AddToCartDto addToCart(CartModel model) {
		CartItem cart = cartRepository.findCartItemByBookIdAndUserId(model.getBookId(), model.getUserId());
		if(cart==null){
		Book book = repository.findById(model.getBookId()).get();
	    cart = CartItem.builder()
					.book(book)
					.userId(model.getUserId())
					.quantity(model.getQuantity())
					.insertDate(new Date())
					.build();
		cart = cartRepository.save(cart);
		}else{
			cart.setQuantity(cart.getQuantity()+model.getQuantity());
			cart.setUpdateDate(new Date());
		}
		return AddToCartDto.builder()
				.cartItemId(cart.getCartId())
				.bookId(cart.getBook().getBookId())
				.userId(cart.getUserId())
				.quantity(cart.getQuantity())
				.insertDate(cart.getInsertDate())
				.updateDate(cart.getUpdateDate())
				.build();
	}

	public List<CartItemsDto> getCartItems(Integer  userId) {
//		List<CartItem> items =cartRepository.findByUserId(userId);
//		List<CartItemsDto> result = items.stream()
//				.map(item ->{
//					return CartItemsDto.builder()
//							.cartItemId(item.getCartId())
//							.bookId(item.getBookId())
//							.title(title)
//							.userId(item.getUserId())
//							.quantity(item.getQuantity())
//							.build();
//				}).collect(Collectors.toList());	
		List<CartItemsDto> result = cartRepository.findByUserId(userId);
		return result;
	}

	public void delete(Integer cartId) {
		cartRepository.deleteById(cartId);
	}

	public void updateCartItem(Integer cartId, Integer quantity) {
		CartItem  item = cartRepository.findById(cartId).get();
		item.setQuantity(quantity);
	}

}
