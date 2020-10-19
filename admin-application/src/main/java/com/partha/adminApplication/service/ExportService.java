package com.partha.adminApplication.service;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;

import com.partha.adminApplication.entities.Book;
import com.partha.adminApplication.repositories.BookRepository;

@Service
public class ExportService {

	@Autowired
	BookRepository bookRepository;

	private static final Logger logger = LoggerFactory.getLogger(ExportService.class);

	//	public ResponseEntity<Resource> getInventoryExport() throws IOException{
	//		List<Book> books = bookRepository.getInventoryExportDetails();
	//		Resource resource =null;
	//		String filename="inventoryExport.csv";
	//		BufferedWriter writer = null;
	//		CSVPrinter csvPrinter = null;
	//
	//		try {
	//			writer = Files.newBufferedWriter(Paths.get(filename));
	//			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
	//					.withHeader("Id","Title","Author","Publisher","AvailableUnits"));
	//			for(Book book : books) {
	//				csvPrinter.printRecord(book.getId()
	//						,book.getTitle()
	//						,book.getAuthor()
	//						,book.getPublisher()
	//						,book.getAvailableUnits()
	//						);
	//			}
	//			writer.flush();
	//		}catch (Exception e) {
	//			if(writer!=null) {
	//				writer.close();
	//			}
	//			if(csvPrinter!=null) {
	//				csvPrinter.close();
	//			}
	//		}
	//		Path path = Paths.get("inventoryExport");
	//		resource = new UrlResource(path.toUri());
	//		if (resource.exists()) {
	//			return ResponseEntity.ok()
	//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
	//					.body(resource);
	//		} else {
	//			logger.error("File not found! Please check the path:- " + filename);
	//			return new ResponseEntity<Resource>(HttpStatus.NO_CONTENT);
	//		}		
	//	}


	public ResponseEntity<byte[]> getInventoryExport() throws IOException{
		List<Book> books = bookRepository.getInventoryExportDetails();

		String filename="inventoryExport.csv";
		BufferedWriter writer = null;
		CSVPrinter csvPrinter = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(baos));
			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
					.withHeader("Id","Title","Author","Publisher","AvailableUnits"));
			for(Book book : books) {
				csvPrinter.printRecord(book.getId()
						,book.getTitle()
						,book.getAuthor()
						,book.getPublisher()
						,book.getAvailableUnits()
						);
			}
			writer.flush();
		}catch (Exception e) {
			if(writer!=null) {
				writer.close();
			}
			if(csvPrinter!=null) {
				csvPrinter.close();
			}
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
				.body(baos.toByteArray());
	}
}

