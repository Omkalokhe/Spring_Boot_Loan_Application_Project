package com.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {

	private String email;

	private String oldpassword;

	private String newpassword;

	private String confirmpassword;

}
