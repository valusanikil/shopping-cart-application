package com.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.exception.domain.CartNotFoundException;
import com.api.exception.domain.ProductNotFoundException;
import com.api.model.Cart;
import com.api.model.Product;
import com.api.model.Quantity;
import com.api.repository.CartRepository;
import com.api.repository.ProductRepository;
import com.api.repository.QuantityRepository;
import com.api.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private QuantityRepository quantityRepository;

	@Override
	public Cart addProductToCart(Long userId, Long productId) throws CartNotFoundException, Exception{
		Cart cart = repository.findCartByCartId(userId);
		if(cart==null) {
			throw new CartNotFoundException("CART_NOT_FOUND_FOR_USER_ID: "+userId);
		}
		List<Quantity> quantitys = cart.getProductQuantity();
		List<Product> products = cart.getProducts();
		Product product = productRepository.findProductByProductId(productId);
		if(products.contains(product)) {
			products.stream().forEach(i-> {
				if(i.getProductId()==productId) {
					for(Quantity q: quantitys) {
						if(q.getProductId()==productId) {
							q.setQuantity(q.getQuantity()+1);
							break;
						}
					}
				}
			});
		} else {
			cart.setTotalProducts(cart.getTotalProducts()+1);
			products.add(product);
			quantitys.add(new Quantity(productId,(long) 1));
		}
		cart.setTotalAmount(cart.getTotalAmount()+product.getPrice());
		cart.setProducts(products);
		cart.setProductQuantity(quantitys);
		return repository.save(cart);
	}

	@Override
	public Cart getCartById(Long id) throws CartNotFoundException, Exception{
		Cart cart = repository.findCartByCartId(id);
		if(cart==null) {
			throw new CartNotFoundException("CART_NOT_FOUND_FOR_USER_ID: "+id);
		}
		return cart;
	}

	@Override
	public Cart removeProductById(Long userId, Long productId) throws CartNotFoundException, ProductNotFoundException, Exception {
		Cart cart = repository.findCartByCartId(userId);
		if(cart==null) {
			throw new CartNotFoundException("CART_NOT_FOUND_FOR_USER_ID: "+userId);
		}
		List<Quantity> quantitys = cart.getProductQuantity();
		List<Product> products = cart.getProducts();
		Product product = productRepository.findProductByProductId(productId);
		Long quantityId = null;
		if(products.contains(product)) {
			products.remove(product);
			for(Quantity q: quantitys) {
				if(q.getProductId()==productId) {
					cart.setTotalAmount(cart.getTotalAmount()-(product.getPrice()*q.getQuantity()));
					quantityId=q.getId();
					quantitys.remove(q);
					break;
				}
			}
			cart.setTotalProducts(cart.getTotalProducts()-1);
			cart.setProductQuantity(quantitys);
			cart.setProducts(products);
			quantityRepository.deleteById(quantityId);
		} else {
			throw new ProductNotFoundException("PRODUCT_NOT_FOUND_IN_THE_CART_WITH_ID : "+productId);
		}
		return repository.save(cart);
	}

	@Override
	public Cart removeAllProducts(Long userId) throws CartNotFoundException, Exception {
		Cart cart = repository.findCartByCartId(userId);
		if(cart==null) {
			throw new CartNotFoundException("CART_NOT_FOUND_FOR_USER_ID: "+userId);
		}
		List<Quantity> quantitys = cart.getProductQuantity();
		cart.setProductQuantity(null);
		for(Quantity q: quantitys) {
			quantityRepository.deleteById(q.getId());
		}
		cart.setProducts(null);
		cart.setTotalAmount(0);
		cart.setTotalProducts((long) 0);
		return repository.save(cart);
	}

	@Override
	public Cart IncrementProductQuantityBy1inTheCart(Long userId, Long productId) throws CartNotFoundException, ProductNotFoundException, Exception {
		Cart cart = repository.findCartByCartId(userId);
		if(cart==null) {
			throw new CartNotFoundException("CART_NOT_FOUND_FOR_USER_ID: "+userId);
		}
		List<Quantity> quantitys = cart.getProductQuantity();
		List<Product> products = cart.getProducts();
		Product product = productRepository.findProductByProductId(productId);
		if(products.contains(product)) {
			for(Quantity q: quantitys) {
				if(q.getProductId()==productId) {
					cart.setTotalAmount(cart.getTotalAmount()+(product.getPrice()));
					q.setQuantity(q.getQuantity()+1);
					break;
				}
			}
			cart.setProductQuantity(quantitys);	
		} else {
			throw new ProductNotFoundException("PRODUCT_NOT_FOUND_IN_THE_CART_WITH_ID : "+productId);
		}
		return repository.save(cart);
	}

	@Override
	public Cart DecrementProductQuantityBy1inTheCart(Long userId, Long productId) throws CartNotFoundException, ProductNotFoundException, Exception {
		Cart cart = repository.findCartByCartId(userId);
		if(cart==null) {
			throw new CartNotFoundException("CART_NOT_FOUND_FOR_USER_ID: "+userId);
		}
		List<Quantity> quantitys = cart.getProductQuantity();
		List<Product> products = cart.getProducts();
		Product product = productRepository.findProductByProductId(productId);
		Long quantityId=null;
		if(products.contains(product)) {
			for(Quantity q: quantitys) {
				if(q.getProductId()==productId) {
					cart.setTotalAmount(cart.getTotalAmount()-(product.getPrice()));
					q.setQuantity(q.getQuantity()-1);
					if(q.getQuantity()==0) {
						quantityId=q.getId();
						quantitys.remove(q);
						products.remove(product);
						cart.setProducts(products);
					}
					break;
				}
			}
			cart.setProductQuantity(quantitys);
			if(quantityId!=null) {
				quantityRepository.deleteById(quantityId);
			}
		} else {
			throw new ProductNotFoundException("PRODUCT_NOT_FOUND_IN_THE_CART_WITH_ID : "+productId);
		}
		return repository.save(cart);
	}

}
