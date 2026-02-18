package com.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dao.CustomerRepository;
import com.dto.EmailDto;
import com.dto.LoginDto;
import com.dto.UserDto;
import com.dto.UserResopnse;
import com.entity.Login;
import com.entity.User;
import com.enumValue.EnumData;
import com.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public UserResopnse registerUserInfo(User user) {
		log.info("In Customer Service Register User Info Method");
		boolean isUserExist = getuserIsExistByEmail(user.getEmail());
		UserResopnse userResponse = new UserResopnse();
		EmailDto emaildto = new EmailDto();
		Login login = new Login();
		if (isUserExist) {
			userResponse.setUsername(user.getEmail());
			userResponse.setMessage("User Email Already Exist.");
			return userResponse;
		}
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String strDate = formatter.format(date);
		user.setCreatedon(strDate);
		boolean flag = false;
		EnumData status = EnumData.ACTIVE;
		if ("A".equals(status.getValue())) {
			flag = true;
		}
		user.setStatus(flag);
		user.setCreatedby(user.getUsername());
		login.setUsername(user.getUsername());
		login.setEmail(user.getEmail());
		login.setPassword(user.getPassword());
		login.setUser(user);
		user.setLogin(login);
		User user1 = customerRepository.save(user);
		if (user1 != null) {
			emaildto.setTo(user1.getEmail());
			emaildto.setSubject("User Register Successfully");
			emaildto.setBody("Hello " + user1.getUsername() + ",\n" + "Your registration completed successfully.");
			restTemplate.postForObject("http://localhost:8001/api/mail/send", emaildto, String.class);
			userResponse.setUsername(user1.getUsername());
			userResponse.setMessage("Thank you ! User Successfully Created.");
		} else {
			userResponse.setMessage("Thank you ! User Not Successfully Created.");
		}
		System.out.println("In Customer Serivce End");
		return userResponse;

	}

	public boolean getuserIsExistByEmail(String email) {
		User userByEmail = customerRepository.findByEmail(email);
		if (userByEmail != null) {
			return true;
		}
		return false;
	}

	@Override
	public ResponseEntity<?> getUserById(int id) {
		log.info("In Customer Service Get User Info By ID Method");
		User user = customerRepository.findById(id);

		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		UserResopnse response = new UserResopnse();
		response.setUsername(null);
		response.setMessage("User not found with id : " + id);
		return new ResponseEntity<UserResopnse>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> getUserByEmail(String email) {
		log.info("In Customer Service Get User Info By Email Method");
		User user = customerRepository.findByEmail(email);
		if (user != null) {
			LoginDto login = LoginDto.builder().id(user.getLogin().getId()).username(user.getLogin().getUsername())
					.email(user.getLogin().getEmail()).build();

			UserDto userDto = UserDto.builder().id(user.getId()).fname(user.getFname()).lname(user.getLname())
					.address(user.getAddress()).username(user.getUsername()).email(user.getEmail()).dob(user.getDob())
					.mobileno(user.getMobileno()).country(user.getCountry()).state(user.getState()).city(user.getCity())
					.zipcode(user.getZipcode()).loginDto(login).build();

			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		}
		UserResopnse response = new UserResopnse();
		response.setUsername(email);
		response.setMessage("User not found with this email");
		return new ResponseEntity<UserResopnse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getUserByUsername(String username) {
		log.info("In Customer Service Get User Info By UserName Method");
		User user = customerRepository.findByUsername(username);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		UserResopnse response = new UserResopnse();
		response.setUsername(username);
		response.setMessage("User not found with this username");
		return new ResponseEntity<UserResopnse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateUserByEmail(String email, User user) {
		log.info("In Customer Service update User Info By email Method");
		User user1 = customerRepository.findByEmail(email);
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String strDate = formatter.format(date);
		if (user1 != null) {
//			user1.setFname(user.getFname());
//			user1.setLname(user.getLname());
//			user1.setAddress(user.getAddress());
//			user1.setEmail(user.getEmail());
//			user1.setMobileno(user.getMobileno());
//			user1.setUsername(user.getUsername());
			BeanUtils.copyProperties(user, user1, "id", "createdby", "createdon");
			user1.setUpdatedby(user.getUsername());
			user1.setUpdatedon(strDate);
			customerRepository.save(user1);
			return new ResponseEntity<User>(user1, HttpStatus.OK);
		}
		UserResopnse response = new UserResopnse();
		response.setUsername(email);
		response.setMessage("User not found with this email");
		return new ResponseEntity<UserResopnse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteUserByEmail(String email) {
		log.info("In Customer Service Delete User Info By email Method");
		User user = customerRepository.findByEmail(email);
		boolean flag = true;
		if (user != null) {
			if (user.isStatus()) {
				EnumData status = EnumData.NONACTIVE;
				if ("N".equals(status.getValue())) {
					flag = false;
				}
				user.setStatus(flag);
				customerRepository.save(user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else {
				UserResopnse response = new UserResopnse();
				response.setUsername(email);
				response.setMessage("User Status is Already NonActive That Why We Can't Proceed this Operation");
				return new ResponseEntity<UserResopnse>(response, HttpStatus.OK);
			}
		}
		UserResopnse response = new UserResopnse();
		response.setUsername(email);
		response.setMessage("User not found with this email That Why We Can't Proceed Delete Operation");
		return new ResponseEntity<UserResopnse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> assignRoleByName(int id, String role) {
		User user = customerRepository.getById(id);
		if (user != null) {
			user.setRoleName(role);
			customerRepository.save(user);
			return new ResponseEntity<String>("Role Assign Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Role is Assign Successfully Because User Is not Present", HttpStatus.OK);
	}

}
