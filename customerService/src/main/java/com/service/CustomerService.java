package com.service;

import org.springframework.http.ResponseEntity;

import com.entity.User;
import com.entity.UserResponse;

public interface CustomerService {

	public UserResponse registerUserInfo(User user);
	
	public ResponseEntity<?> getUserById(int id);
	
	public ResponseEntity<?> getUserByEmail(String email);
	
	public ResponseEntity<?> getUserByUsername(String username);
	
	public ResponseEntity<?> updateUserByEmail(String email,User user);
	
	public ResponseEntity<?> deleteUserByEmail(String email);
}
