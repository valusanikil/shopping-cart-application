package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;

import com.api.exception.domain.CartNotFoundException;
import com.api.exception.domain.ProductNotFoundException;
import com.api.model.Cart;
import com.api.service.CartService;

@RestController
@RequestMapping("/user/cart")
public class CartController {
	
	@Autowired
	private CartService service;
	
	@PutMapping("/{userId}/addProduct/{productId}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable Long userId,@PathVariable Long productId) throws CartNotFoundException,Exception {
		return new ResponseEntity<>(service.addProductToCart(userId, productId),OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Cart> getProductByUserId(@PathVariable Long userId) throws CartNotFoundException, Exception {
		return new ResponseEntity<>(service.getCartById(userId),OK);
	}
	
	@PutMapping("/{userId}/removeProduct/{productId}")
	public ResponseEntity<Cart> removeProductFromCartById(@PathVariable Long userId,@PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException,Exception {
		return new ResponseEntity<>(service.removeProductById(userId, productId),OK);
	}
	
	@PutMapping("/{userId}/removeProduct/all")
	public ResponseEntity<Cart> removeAllProductsFromCart(@PathVariable Long userId) throws Exception {
		return new ResponseEntity<>(service.removeAllProducts(userId),OK);
	}
	
	@PutMapping("/{userId}/productQuantity/increment/{productId}")
	public ResponseEntity<Cart> incrementProductQuantityBy1inCart(@PathVariable Long userId,@PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException,Exception {
		return new ResponseEntity<>(service.IncrementProductQuantityBy1inTheCart(userId, productId),OK);
	}
	
	@PutMapping("/{userId}/productQuantity/decrement/{productId}")
	public ResponseEntity<Cart> decrementProductQuantityBy1inCart(@PathVariable Long userId,@PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException,Exception {
		return new ResponseEntity<>(service.DecrementProductQuantityBy1inTheCart(userId, productId),OK);
	}
	
}
