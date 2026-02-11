package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ChangePasswordDto;
import com.dto.ForgetPasswordDto;
import com.service.CustomerLoginService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {

	@Autowired
	private CustomerLoginService customerLoginService;

	@PostMapping(value = "/api/cust/forgetpassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> forgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto) {
		log.info("In Login Controller");
		ResponseEntity<?> response = customerLoginService.forgetPassword(forgetPasswordDto);
		return response;
	}
	@PutMapping(value = "/api/cust/changepassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
		log.info("In Login Controller");
		ResponseEntity<?> response = customerLoginService.changePassword(changePasswordDto);
		return response;
	}
};
