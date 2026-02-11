package com.service;

import org.springframework.http.ResponseEntity;

import com.dto.ChangePasswordDto;
import com.dto.ForgetPasswordDto;

public interface CustomerLoginService {
	
	public ResponseEntity<?> forgetPassword(ForgetPasswordDto forgetPasswordDto);
	
	public ResponseEntity<?> changePassword(ChangePasswordDto changePasswordDto);
	
}
