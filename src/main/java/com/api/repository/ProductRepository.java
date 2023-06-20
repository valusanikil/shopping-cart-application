package com.api.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.model.Product;

@Repository
@Primary
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Product findProductByProductId(Long id);
	
	Product findProductByProductName(String name);
	@Query(value = "select * from product where product_type =:category",nativeQuery = true)
	List<Product> findByCategory(String category);

}
