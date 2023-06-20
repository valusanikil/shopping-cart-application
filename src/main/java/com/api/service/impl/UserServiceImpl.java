package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.exception.domain.EmailExistsException;
import com.api.exception.domain.UserNotFoundException;
import com.api.exception.domain.UsernameExistsException;
import com.api.model.Cart;
import com.api.model.Quantity;
import com.api.model.User;
import com.api.repository.UserRepository;
import com.api.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public List<User> getAllUsers() throws Exception {
		return repository.findAll();
	}

	@Override
	public User saveuser(User user) throws UsernameExistsException, EmailExistsException, Exception {
		validateUsernameAndEmail(user.getUsername(), user.getEmail());
		User tempUser = repository.save(user);
		Cart cart = new Cart(tempUser.getUserId(),(long) 0,0);
		cart.setProductQuantity(new ArrayList<Quantity>());
		tempUser.setCart(cart);
		return repository.save(tempUser);
	}

	@Override
	public User findByUserId(Long id) throws UserNotFoundException, Exception {
		User user = repository.findUserByUserId(id);
		if(user==null) {
			throw new UserNotFoundException("USER_NOT_FOUND_BY_ID: "+id);
		}
		return user;
	}

	@Override
	public User findByUsername(String username) throws UserNotFoundException, Exception {
		User user = repository.findUserByUsername(username);
		if(user==null) {
			throw new UserNotFoundException("USER_NOT_FOUND_BY_USERNAME: "+username);
		}
		return user;
	}

	@Override
	public User findByEmail(String email) throws UserNotFoundException, Exception {
		User user = repository.findUserByEmail(email);
		if(user==null) {
			throw new UserNotFoundException("USER_NOT_FOUND_BY_EMAIL: "+email);
		}
		return repository.findUserByEmail(email);
	}

	@Override
	public User updateUser(Long id, User user) throws UserNotFoundException, UsernameExistsException, EmailExistsException, Exception {
		User updatedUser = validateNewUsernameAndEmail(id, user.getUsername(), user.getEmail());
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setUsername(user.getUsername());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setPhoneNo(user.getPhoneNo());
		return updatedUser;
	}
	
	private void validateUsernameAndEmail(String username, String email) throws UsernameExistsException, EmailExistsException {
		User tempUsername = repository.findUserByUsername(username);
		User tempEmail = repository.findUserByEmail(email);
		if(tempUsername!=null) {
			throw new UsernameExistsException("USERNAME_ALREADY_EXISTS: "+username);
		}
		if(tempEmail!=null) {
			throw new EmailExistsException("EMAIL_ALREADY_EXISTS: "+email);
		}
	}
	
	private User validateNewUsernameAndEmail(Long id, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistsException, EmailExistsException {
        User userByNewUsername = repository.findUserByUsername(newUsername);
        User userByNewEmail = repository.findUserByEmail(newEmail);
        User currentUser = repository.findUserByUserId(id);
        if(currentUser == null) {
             throw new UserNotFoundException("NO_USER_FOUND_BY_ID : " + id);
        }
        if(userByNewUsername != null && !currentUser.getUserId().equals(userByNewUsername.getUserId())) {
        	throw new UsernameExistsException("USERNAME_ALREADY_EXISTS: "+newUsername);
        }
        if(userByNewEmail != null && !currentUser.getUserId().equals(userByNewEmail.getUserId())) {
        	throw new EmailExistsException("EMAIL_ALREADY_EXISTS: "+newEmail);
        }
        return currentUser;
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		repository.deleteById(id);
	}

}
 