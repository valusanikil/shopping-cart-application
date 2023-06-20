package com.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.api.exception.domain.ProductNotFoundException;
import com.api.model.Product;
import com.api.repository.ProductRepository;
import com.api.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> getAllProducts() throws Exception{
		return repository.findAll();
	}

	@Override
	public Product getProductById(Long id) throws ProductNotFoundException, Exception {
		Product product = repository.findProductByProductId(id);
		if(product==null) {
			throw new ProductNotFoundException("PRODUCT_NOT_FOUND_WITH_ID: "+id);
		}
		return product;
	}

	@Override
	public Product getProductByName(String name) throws ProductNotFoundException, Exception {
		Product product = repository.findProductByProductName(name);
		if(product==null) {
			throw new ProductNotFoundException("PRODUCT_NOT_FOUND_WITH_NAME: +" +name);
		}
		return product;
	}

	@Override
	public List<Product> getProductByCategory(String category) throws Exception {
		List<Product> products = repository.findByCategory(category);
		return products;
	}

}
