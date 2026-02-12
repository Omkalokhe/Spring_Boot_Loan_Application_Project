package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserResopnse;
import com.entity.User;
import com.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/api/cust/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResopnse> registerUserInfo(@RequestBody User user) {
		log.info("In Customer Controller");
		UserResopnse userResponse = customerService.registerUserInfo(user);
		return new ResponseEntity<UserResopnse>(userResponse, HttpStatus.CREATED);
	}

	@GetMapping(value = "/api/cust/{id}")
	public ResponseEntity<?> getUserDetailsById(@PathVariable("id") int id) {
		log.info("In Customer Controller Get Method");
		ResponseEntity<?> response = customerService.getUserById(id);
		return response;
	}
	
	@GetMapping(value = "/api/cust/email/{email}")
	public ResponseEntity<?> getUserDetailsByEmail(@PathVariable("email") String email) {
		log.info("In Customer Controller Get Method");
		ResponseEntity<?> response = customerService.getUserByEmail(email);
		return response;
	}
	
	@GetMapping(value = "/api/cust/username/{username}")
	public ResponseEntity<?> getUserDetails(@PathVariable("username") String username) {
		log.info("In Customer Controller Get Method");
		ResponseEntity<?> response = customerService.getUserByUsername(username);
		return response;
	}
	
	@PutMapping(value = "/api/cust/update/{email}")
	public ResponseEntity<?> updateUserDetailsByEmail(@RequestBody User user,@PathVariable("email") String email){
		ResponseEntity<?> response = customerService.updateUserByEmail(email, user);
		return response;
	}
	
	@DeleteMapping(value = "/api/cust/delete/{email}")
	public ResponseEntity<?> deleteUserDetailsByEmail(@PathVariable("email") String email){
		ResponseEntity<?> response = customerService.deleteUserByEmail(email);
		return response;
	}
	
	
	
}
