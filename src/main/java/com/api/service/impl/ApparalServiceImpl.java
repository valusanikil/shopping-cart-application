package com.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.exception.domain.ProductNameExistsException;
import com.api.model.Apparal;
import com.api.model.Product;
import com.api.repository.ApparalRepository;
import com.api.repository.ProductRepository;
import com.api.service.ApparalService;

@Service
public class ApparalServiceImpl implements ApparalService {
	
	@Autowired
	private ApparalRepository apparalRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Apparal saveApparal(Apparal apparal) throws ProductNameExistsException, Exception {
		validateProductName(apparal.getProductName());
		return apparalRepository.save(apparal);
	}
	
	private void validateProductName(String name) throws ProductNameExistsException {
		Product product = productRepository.findProductByProductName(name);
		if(product!=null) {
			throw new ProductNameExistsException("PRODUCT_WITH_NAME "+ name +" ALREADY_EXISTS");
		}
	}

}
