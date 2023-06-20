package com.api.service;

import com.api.exception.domain.ProductNotFoundException;
import com.api.model.Cart;

public interface CartService {
	
	public Cart addProductToCart(Long userId, Long productId) throws Exception;
	
	public Cart getCartById(Long id) throws Exception;
	
	public Cart removeProductById(Long userId, Long productId) throws ProductNotFoundException, Exception;
	
	public Cart removeAllProducts(Long userId) throws Exception;
	
	public Cart IncrementProductQuantityBy1inTheCart(Long id, Long productId) throws ProductNotFoundException,Exception;
	
	public Cart DecrementProductQuantityBy1inTheCart(Long id, Long productId) throws ProductNotFoundException, Exception;

}
