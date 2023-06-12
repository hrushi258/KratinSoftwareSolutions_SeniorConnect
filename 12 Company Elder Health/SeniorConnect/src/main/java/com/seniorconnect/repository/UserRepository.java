package com.seniorconnect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seniorconnect.entities.User;
import java.lang.String;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByEmailIgnoreCase(String email);
	//get a user by their email address

	List<User> findByEmail(String email);
	//gets a user by their username or email address. This can be helpful for login or authentication functionality

	boolean existsByUsernameIgnoreCase(String username);
	//checks if username exists

	boolean existsByEmailIgnoreCase(String email);
	//checks if email address exists

	List<User> findByUserName(String username);

	List<User> findByFirstNameContainingOrLastNameContainingOrUserNameContaining(String keyword, String keyword2,
			String keyword3);
	
}
