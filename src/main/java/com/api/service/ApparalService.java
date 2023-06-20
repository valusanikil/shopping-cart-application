package com.api.service;

import com.api.exception.domain.ProductNameExistsException;
import com.api.model.Apparal;

public interface ApparalService {
	
	public Apparal saveApparal(Apparal apparal) throws ProductNameExistsException, Exception;

}
