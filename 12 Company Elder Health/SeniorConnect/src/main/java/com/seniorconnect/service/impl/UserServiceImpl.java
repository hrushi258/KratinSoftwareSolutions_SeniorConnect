package com.seniorconnect.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.seniorconnect.entities.RoleName;
import com.seniorconnect.entities.User;
import com.seniorconnect.exception.ResourceNotFoundException;
import com.seniorconnect.repository.UserRepository;
import com.seniorconnect.service.UserService;

public class UserServiceImpl implements UserService {

	 private final UserRepository userRepository;

	    public UserServiceImpl(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @Override
	    public User createUser(User userDto) {
	        return userRepository.save(userDto);
	    }

	    @Override
	    public User updateUser(Long userId, User userDto) {
	        User existingUser = getUserById(userId);
	        if (existingUser != null) {
	            // Update the existing user with the fields from userDto
	            existingUser.setFirstName(userDto.getFirstName());
	            existingUser.setLastName(userDto.getLastName());
	            existingUser.setBio(userDto.getBio());
	            existingUser.setEmail(userDto.getEmail());
	            existingUser.setPassword(userDto.getPassword());
	            existingUser.setPhone(userDto.getPhone());
	            existingUser.setSex(userDto.getSex());
	            existingUser.setUpdatedBy(userDto.getUpdatedBy());
	            existingUser.setUpdatedDate(LocalDateTime.now());
	            existingUser.setUserName(userDto.getUserName());
	            existingUser.getRoles().addAll(userDto.getRoles());

	            return userRepository.save(existingUser);
	        } else {
	            throw new ResourceNotFoundException("User not found with id: " + userId);
	        }
	    }
	    @Override
	    public void deleteUser(Long userId) {
	        User existingUser = getUserById(userId);
	        if (existingUser != null) {
	            userRepository.delete(existingUser);
	        } else {
	            throw new ResourceNotFoundException("User not found with id: " + userId);
	        }
	    }

	    @Override
	    public User getUserById(Long userId) {
	        return userRepository.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
	    }

	    @Override
	    public User getUserByUsername(String username) {
	        return userRepository.findByUserName(username).get(0);
	                
	    }

	    @Override
	    public User getUserByEmail(String email) {
	        return userRepository.findByEmail(email).get(0);
	               
	    }

	    @Override
	    public List<User> getUsersByKeyword(String keyword) {
	        // Implement the logic to search users by a keyword
	        return userRepository.findByFirstNameContainingOrLastNameContainingOrUserNameContaining(keyword, keyword, keyword);
	    }


	    @Override
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    @Override
	    public boolean isUsernameAvailable(String username) {
	        return !userRepository.existsByUsernameIgnoreCase(username);
	    }

	    @Override
	    public boolean isEmailAvailable(String email) {
	        return !userRepository.existsByEmailIgnoreCase(email);
	    }
}
