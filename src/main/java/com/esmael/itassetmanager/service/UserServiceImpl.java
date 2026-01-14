package com.esmael.itassetmanager.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmael.itassetmanager.entities.User;
import com.esmael.itassetmanager.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	
	private final UserRepository userRepository;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}

	@Override
	public User createUser(User user) {
		if(userRepository.findByEmail(user.getEmail()).isPresent()){
			return null;
			
		}
		return userRepository.save(user);
	}

	
	@Override
	public User getUserById(Long id) {
		
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User getUserByEmail(String email) {
		
		return userRepository.findByEmail(email).orElse(null);
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User updateUser(Long id, User user) {
		User existingUser = userRepository.findById(id).orElse(null);
		
		if(existingUser == null) {
			return null;
		}
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
	    existingUser.setEmail(user.getEmail());
	    existingUser.setDepartment(user.getDepartment());
		
		return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser(Long id) {
		
		 if (userRepository.existsById(id)) {
		        userRepository.deleteById(id);
		    }
	}

}
