package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findUserByUserId(Long id);
	
	User findUserByUsername(String username);

	User findUserByEmail(String email);
	
}
