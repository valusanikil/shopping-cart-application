package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.model.Product;
import com.api.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) throws Exception {
		Product product = service.getProductById(id);
		return new ResponseEntity<>(product,OK);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Product> getProductByName(@PathVariable String name) throws Exception {
		Product product = service.getProductByName(name);
		return new ResponseEntity<>(product,OK);
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category) throws Exception {
		List<Product> products = service.getProductByCategory(category);
		return new ResponseEntity<>(products,OK);
	}
	
}
