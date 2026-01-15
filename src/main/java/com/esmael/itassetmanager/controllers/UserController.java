package com.esmael.itassetmanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esmael.itassetmanager.entities.User;
import com.esmael.itassetmanager.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//crud 
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		var currentUser = userService.getUserById(id);
		if(currentUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(currentUser);
		
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		User user = userService.getUserById(id);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
		
		User existingUser = userService.getUserById(id);
		if(existingUser == null) {
			return ResponseEntity.notFound().build();
		}
		user.setId(id);
		User updatedUser = userService.updateUser(id, user);
		return ResponseEntity.ok(updatedUser);
		
		
	}
	
	
	

}
