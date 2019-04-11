package com.partha.adminApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.partha.adminApplication.entities.Book;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@RequestMapping("/add")
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBook";
	}

}
