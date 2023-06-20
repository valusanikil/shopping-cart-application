package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.model.User;
import com.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/list")
	public ResponseEntity<List<User>> getAllUsers() throws Exception {
		List<User> users= service.getAllUsers();
		return new ResponseEntity<>(users,OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception {
		User user = service.findByUserId(id);
		return new ResponseEntity<>(user,OK);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) throws Exception {
		User user = service.findByUsername(username);
		return new ResponseEntity<>(user,OK);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) throws Exception {
		User user = service.findByEmail(email);
		return new ResponseEntity<>(user,OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception {
		User newUser = service.saveuser(user);
		return new ResponseEntity<>(newUser,OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) throws Exception {
		User updatedUser = service.updateUser(id, user);
		return new ResponseEntity<>(updatedUser,OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUserById(@PathVariable Long id) throws Exception {
		service.deleteUser(id);
	}

}
