package com.partha.databaseService;

//import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
//import org.springframework.util.ResourceUtils;

import com.partha.databaseService.constants.AppConstant;
import com.partha.databaseService.entities.Book;
import com.partha.databaseService.repositories.BookRepository;


/**
 * this class is to do the data loading into the book table
 * on start of the application
 * @author partha biswas
 *
 */
@Component
public class DatabaseServiceSetup implements CommandLineRunner{

	@Autowired
	private BookRepository repository;
	

//	@Autowired
//    ResourceLoader resourceLoader;
	
	@Override
	public void run(String... args) throws Exception {
		insertBookData();
	}


	private void insertBookData(){
		try{
			repository.deleteAll();
			Date date= new Date();
			List<Book> list = new ArrayList<>();
			
			Book book = Book.builder()
					.title("Pro Ajax and Java")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
//					.image(FileUtils.readFileToByteArray(
//								//ResourceUtils.getFile("classpath:book/7.png")  //work from within sts
//								new File( getClass().getResource("/book/7.png").getFile()) //works from within sts
//							))
					.image(
							IOUtils.toByteArray(
									//new ClassPathResource("/book/7.png").getInputStream()
									new ClassPathResource("/static/image/book/1.png").getInputStream()
							))
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();
			list.add(book);
			
			book = Book.builder()
					.title("OCA/OCP Java 8 Programmer")
					.author("author2")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/2.png").getInputStream()
							))
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();
			
			list.add(book);
			
			book = Book.builder()
					.title("Head first Java")
					.author("author3")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/3.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();
			list.add(book);
			
			book = Book.builder()
					.title("Java SE 7 Programmer Exams")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/4.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Learning Java")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/5.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Cloud computing from beginning to end")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("cloud")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/6.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Cloud computing bible")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/7.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Architecting the cloud")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/8.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("OpenStack Cloud Computing Cookbook")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/9.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Cloud computing concepts technology architecture")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/10.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Cloud computing a hands on approach")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/11.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Architecting the cloud Enterprise Architecture patterns and Cloud computing")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/12.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			
			book = Book.builder()
					.title("Apache Cloudstack Cloud Computing")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/13.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Learning AWS")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/14.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Amazon Web Services Bootcamp")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/15.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Cloud computing with Google Chrome")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/16.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Interactive applications in python")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(175)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/17.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Python for data science")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(500)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/18.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Programming in python")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(100)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/19.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Python 3 for absolute beginners")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(150)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/20.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
//			book = Book.builder()
//					.title("Machine learning in python")
//					.author("author1")
//					.publisher("some publisher")
//					.publicationDate(date)
//					.language("english")
//					.pages(1500)
//					.format("paperback")
//					.isbn("8767987979797")
//					.category("programming")
//					.availableUnits(200)
//					.price(new BigDecimal(200.00))
//					.active(true)
//					.image(
//							IOUtils.toByteArray(
//									new ClassPathResource("/static/image/book/21.png").getInputStream()
//									)					
//							)
//					.insertDate(date)
//					.updateDate(date)
//					.description(AppConstant.BOOK_DESCRIPTION)
//					.build();		
//			list.add(book);
			
			book = Book.builder()
					.title("Classic computer science problems in python")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/22.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			book = Book.builder()
					.title("Head first python")
					.author("author1")
					.publisher("some publisher")
					.publicationDate(date)
					.language("english")
					.pages(1500)
					.format("paperback")
					.isbn("8767987979797")
					.category("programming")
					.availableUnits(200)
					.price(new BigDecimal(200.00))
					.active(true)
					.image(
							IOUtils.toByteArray(
									new ClassPathResource("/static/image/book/23.png").getInputStream()
									)					
							)
					.insertDate(date)
					.updateDate(date)
					.description(AppConstant.BOOK_DESCRIPTION)
					.build();		
			list.add(book);
			
			repository.saveAll(list);
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

}
