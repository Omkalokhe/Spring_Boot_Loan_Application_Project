package com.dto;

import com.entity.Login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

	private int id;

	private String fname;

	private String lname;

	private String address;

	private String username;

	private String email;

	private String dob;

	private String mobileno;

	private String country;

	private String state;

	private String city;

	private String zipcode;

	private LoginDto loginDto;

}
