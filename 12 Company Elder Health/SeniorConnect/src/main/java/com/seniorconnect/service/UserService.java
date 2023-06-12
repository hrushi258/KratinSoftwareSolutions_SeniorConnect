package com.seniorconnect.service;

import java.util.List;

import com.seniorconnect.entities.User;

public interface UserService {
	User createUser(User userDto);
    User updateUser(Long userId, User userDto);
    void deleteUser(Long userId);
    User getUserById(Long userId);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getUsersByKeyword(String keyword);
    List<User> getAllUsers();
    boolean isUsernameAvailable(String username);
    boolean isEmailAvailable(String email);
    
}
