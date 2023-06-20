package com.api.service;

import java.util.List;

import com.api.exception.domain.EmailExistsException;
import com.api.exception.domain.UserNotFoundException;
import com.api.exception.domain.UsernameExistsException;
import com.api.model.User;

public interface UserService {
	
	public List<User> getAllUsers() throws Exception;
	
	public User saveuser(User user) throws UsernameExistsException, EmailExistsException, Exception;
	
	public User findByUserId(Long id) throws UserNotFoundException, Exception;
	
	public User findByUsername(String username) throws UserNotFoundException, Exception;
	
	public User findByEmail(String email) throws UserNotFoundException, Exception;
	
	public User updateUser(Long id, User user) throws UserNotFoundException,UsernameExistsException,EmailExistsException, Exception;
	
	public void deleteUser(Long id) throws Exception;
	
}
