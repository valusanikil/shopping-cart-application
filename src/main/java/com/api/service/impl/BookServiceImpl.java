package com.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.exception.domain.ProductNameExistsException;
import com.api.model.Book;
import com.api.model.Product;
import com.api.repository.BookRepository;
import com.api.repository.ProductRepository;
import com.api.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Book saveProductBook(Book book) throws ProductNameExistsException, Exception {
		validateProductName(book.getProductName());
		return bookRepository.save(book);
	}

	private void validateProductName(String name) throws ProductNameExistsException {
		Product product = productRepository.findProductByProductName(name);
		if(product!=null) {
			throw new ProductNameExistsException("PRODUCT_WITH_NAME "+ name +" ALREADY_EXISTS");
		}
	}
	
}
