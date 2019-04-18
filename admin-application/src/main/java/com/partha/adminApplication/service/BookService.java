package com.partha.adminApplication.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
//import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
//import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.partha.adminApplication.builder.BookBuilder;
import com.partha.adminApplication.builder.BookDtoBuilder;
import com.partha.adminApplication.dto.BookDto;
import com.partha.adminApplication.entities.Book;
import com.partha.adminApplication.model.BookModel;
import com.partha.adminApplication.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
//	@Value("${book.image.location}")
//	private String imageLocation;

	@Transactional
	public Book addBook(BookModel model) throws Exception{
		Date currentDate = new Date();
		Book book = Book.builder()
				.title(model.getBookName())
				.author(model.getAuthor())
				.category(model.getCategory())
				.availableUnits(model.getAvailableUnits())
				.price(model.getPrice())
				.active(model.isActive())
				.publisher(model.getPublisher())
				.language(model.getLanguage())
				.pages(model.getPages())
				.isbn(model.getIsbn())
				.format(model.getFormat())
				.description(model.getDescription())
				.publicationDate(currentDate)
				.insertDate(currentDate)
				.updateDate(currentDate)
				.image(model.getBookImage()!=null?model.getBookImage().getBytes():null)
				.build();
		book = repository.save(book);

//		//now saving the image
//		MultipartFile bookImage = model.getBookImage();
//		byte[] bytes = bookImage.getBytes();
//		String name = book.getId() + ".png";
//		
//		//saving under src main resources
////		BufferedOutputStream stream = new BufferedOutputStream(
////				new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
//		
//		//saving under specified location
//		BufferedOutputStream stream = new BufferedOutputStream(
//					new FileOutputStream(new File(imageLocation + name)));
//		
//		stream.write(bytes);
//		stream.close();

		return book;
	}
	
	@Transactional
	public Book updateBook(BookModel model) throws Exception{
		Book book = repository.findById(model.getId()).get();
		book = BookBuilder.build(book, model);
		return repository.save(book);
	}
	

	@Transactional
	public void deleteBook(int id){
		repository.deleteById(id);
	}

	@Transactional
	public List<BookDto> showBooks(){
		List<Book> books = ((List<Book>)repository.findAll());
		List<BookDto> bookList =books.stream()
				.map(book -> BookDtoBuilder.bookDto(book))
				.collect(Collectors.toList());
		return bookList	;			
	}

	@Transactional
	public BookDto getBookDetails(int id) {
		BookDto dto = null;
		Optional<Book> result = repository.findById(id);
		if(result.isPresent()){
			Book book =result.get();
			dto = BookDto.builder()
					.id(book.getId())
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

//			//find and load the image onto the dto from src main resources
//			String filename= book.getId()+".png";
//			
//			File file = null;
//			try {
//				// to read from src/main/resources
//				//file = ResourceUtils.getFile("classpath:static/image/book/"+filename);
//				
//				// to read from some location
//				file = new File(imageLocation+filename);
//				
//				System.out.println("file exists:" +file.exists());
//							
//				byte[] byteArray = FileUtils.readFileToByteArray(file);
//				String base64string = Base64.getEncoder().encodeToString(byteArray);
//				
//				dto.setImage(base64string);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}	
		return dto;
	}



}