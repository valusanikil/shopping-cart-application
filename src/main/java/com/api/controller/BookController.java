package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;

import com.api.exception.domain.ProductNameExistsException;
import com.api.model.Book;
import com.api.service.BookService;

@RestController
@RequestMapping("/product/book")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@PostMapping("/add")
	public ResponseEntity<Book> addProductBook(@RequestBody Book book) throws ProductNameExistsException, Exception {
		return new ResponseEntity<>(service.saveProductBook(book),OK);
	}
}
