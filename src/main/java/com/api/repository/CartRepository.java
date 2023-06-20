package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Cart findCartByCartId(Long id);

}
