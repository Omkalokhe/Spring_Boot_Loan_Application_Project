package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.CustomerRepository;
import com.dao.LoginRepository;
import com.dto.ChangePasswordDto;
import com.dto.ForgetPasswordDto;
import com.entity.Login;
import com.entity.User;
import com.service.CustomerLoginService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerLoginServiceImpl implements CustomerLoginService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public ResponseEntity<?> forgetPassword(ForgetPasswordDto forgetPasswordDto) {
		log.info("In Customer Login Service Forget Password Method");
		User user = customerRepository.findByEmailAndUsername(forgetPasswordDto.getEmail(),
				forgetPasswordDto.getUsername());
		if (user != null) {
			Login login = user.getLogin();
			if (forgetPasswordDto.getNewpassword().equals(forgetPasswordDto.getConfirmpassword())) { // condition is
																										// from ui side
				if (user.getPassword().equals(forgetPasswordDto.getConfirmpassword())) {
					System.out.println("Your Old Password And New Password is Same please Try Again..!");
					return new ResponseEntity<String>("Your Old Password And New Password is Same please Try Again..!",
							HttpStatus.OK);
				}
				user.setPassword(forgetPasswordDto.getConfirmpassword());
				login.setPassword(forgetPasswordDto.getConfirmpassword());
				customerRepository.save(user);
				return new ResponseEntity<String>("New Password Set Successfully", HttpStatus.OK);
			}
			return new ResponseEntity<String>("New Password And Confirm Password Didn't Match", HttpStatus.OK);
		}
		return new ResponseEntity<String>("User Details Invalid", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> changePassword(ChangePasswordDto changePasswordDto) {
		Login login = loginRepository.findByEmail(changePasswordDto.getEmail());
		if (login != null) {
			User user = login.getUser();
			if (login.getEmail().equals(changePasswordDto.getEmail())
					&& login.getPassword().equals(changePasswordDto.getOldpassword())) {
				if (changePasswordDto.getNewpassword().equals(changePasswordDto.getConfirmpassword())) { // UI side Checking Condition
					if (login.getPassword().equals(changePasswordDto.getConfirmpassword())) {
						System.out.println("Your Old Password And New Password is Same please Try Again..!");
						return new ResponseEntity<String>(
								"Your Old Password And New Password is Same please Try Again..!", HttpStatus.OK);
					}
					login.setPassword(changePasswordDto.getConfirmpassword());
					user.setPassword(changePasswordDto.getConfirmpassword());
//					loginRepository.save(login);
					customerRepository.save(user);
					return new ResponseEntity<String>("Password Change Successfully", HttpStatus.OK);
				}
				return new ResponseEntity<String>("New Password And Confirm Password Didn't Match", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Login credentials invalid", HttpStatus.OK);
		}
		return new ResponseEntity<String>("User Details Invalid", HttpStatus.OK);
	}

}
