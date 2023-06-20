package com.api.service;

import com.api.exception.domain.ProductNameExistsException;
import com.api.model.Book;

public interface BookService {

	public Book saveProductBook(Book book) throws ProductNameExistsException, Exception;
	
}
