package com.partha.productService.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.partha.productService.builder.BookDtoBuilder;
import com.partha.productService.dto.AddToCartDto;
import com.partha.productService.dto.BookDto;
import com.partha.productService.dto.CartItemsDto;
import com.partha.productService.dto.OrderDetailsDto;
import com.partha.productService.dto.OrderHistoryDto;
import com.partha.productService.dto.OrderMasterDto;
import com.partha.productService.entities.Book;
import com.partha.productService.entities.CartItem;
import com.partha.productService.entities.OrderDetail;
import com.partha.productService.entities.OrderMaster;
import com.partha.productService.model.CartModel;
import com.partha.productService.model.SearchBook;
import com.partha.productService.repositories.BookRepository;
//import com.partha.productService.repositories.CartItemRepository;
import com.partha.productService.repositories.CartRepository;
import com.partha.productService.repositories.OrderDetailsRepository;
import com.partha.productService.repositories.OrderMasterRepository;
import com.partha.productService.response.OrderHistoryResponse;

@Service
public class BookService {
	
	public static final  Logger logger = LoggerFactory.getLogger(BookService.class);
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CartRepository	cartRepository;
	
	@Autowired
	private OrderMasterRepository orderMasterRepository;
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
//	@Autowired
//	private CartItemRepository cartItemRepository;
	
	//methods that correspond to controllers
	public List<BookDto> getAllBooks(){
		logger.info("BookService.getAllBooks() :: start");
		List<Book> books = ((List<Book>)repository.findAll());
		List<BookDto> bookList =books.stream()
				.filter(book -> book.getActive())
				.map(book -> BookDtoBuilder.bookDto(book))
				.collect(Collectors.toList());
		logger.info("BookService.getAllBooks() :: end");
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
					.active(true)
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
		cartRepository.save(item);
	}

	public void placeOrder(Integer userId) {
		//find cart items for the user
		List<CartItemsDto> cartItems = cartRepository.findByUserId(userId);
			
		//inserting into order master
		OrderMaster orderMaster = OrderMaster.builder()
			.userId(userId)
			.deliveryAddress("some address")
			.insertDate(new Date())
			//.active(true)
			.build();
		OrderMaster savedMaster = orderMasterRepository.save(orderMaster);
				
		//inserting in order detail
		List<OrderDetail> orderDetails = cartItems.stream().map(item -> {
			return OrderDetail.builder()
			//.orderId(savedMaster.get)
			.insertDate(new Date())
			.orderId(savedMaster.getId())
			.bookId(item.getBookId())
			.quantity(item.getQuantity())
			.build();
		}).collect(Collectors.toList());
		orderDetailsRepository.saveAll(orderDetails);
		
		
		//then removing the items from the cart table
		Set<Integer> cartIds = cartItems.stream().map(cart -> cart.getCartId())
		.collect(Collectors.toSet());	
		cartRepository.updateActiveFlag(cartIds);
	}

	public ResponseEntity<?> getOrderHistory(Integer userId) {
		List<OrderHistoryDto> ordersList = orderDetailsRepository.getOrderHistory(userId);
		Map<Integer, OrderMasterDto> map = new TreeMap(Collections.reverseOrder());
		for(OrderHistoryDto order : ordersList) {
			OrderMasterDto orderMaster = map.get(order.getOrderId());
			if(orderMaster==null){
				orderMaster =  OrderMasterDto.builder()
						.orderId(order.getOrderId())
						.orderDate(order.getOrderDate())
						.orderDetails(new ArrayList<OrderDetailsDto>())
						.deliverAddress(order.getDeliveryAddress())
						.build();
				
				orderMaster.getOrderDetails().add(
						OrderDetailsDto.builder()
						.id(order.getOrderDetailsId())
						.bookId(order.getBookid())
						.quantity(order.getQuantity())
						.title(order.getTitle())
						.author(order.getAuthor())
						.image(order.getImage())
						.description(order.getDescription())
						.price(order.getPrice())
						.build()
						);
				
				map.put(order.getOrderId(), orderMaster);
				
			}else {
				orderMaster.getOrderDetails().add(
						OrderDetailsDto.builder()
						.id(order.getOrderDetailsId())
						.bookId(order.getBookid())
						.quantity(order.getQuantity())
						.title(order.getTitle())
						.author(order.getAuthor())
						.image(order.getImage())
						.description(order.getDescription())
						.price(order.getPrice())
						.build()
					);
			}
			orderMaster.setTotalAmount(orderMaster.getTotalAmount() + (order.getPrice()*order.getQuantity()));
		}
		
		List<OrderMasterDto> orders = map.values().stream().collect(Collectors.toList());
		
		OrderHistoryResponse response = OrderHistoryResponse.builder()
			.orders(orders)
			.build();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
