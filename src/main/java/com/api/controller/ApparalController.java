package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;

import com.api.exception.domain.ProductNameExistsException;
import com.api.model.Apparal;
import com.api.service.ApparalService;

@RestController
@RequestMapping("/product/apparal")
public class ApparalController {

	@Autowired
	private ApparalService service;
	
	@PostMapping("/add")
	public ResponseEntity<Apparal> addProductApparal(@RequestBody Apparal apparal) throws ProductNameExistsException, Exception {
		return new ResponseEntity<>(service.saveApparal(apparal),OK);
	}
	
}
