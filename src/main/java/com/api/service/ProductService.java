package com.api.service;

import java.util.List;
import com.api.exception.domain.ProductNotFoundException;
import com.api.model.Product;


public interface ProductService {
	
	public List<Product> getAllProducts() throws Exception;
	
	public Product getProductById(Long id) throws ProductNotFoundException, Exception;
	
	public Product getProductByName(String name) throws ProductNotFoundException, Exception;
	
	public List<Product> getProductByCategory(String category) throws Exception;

}
