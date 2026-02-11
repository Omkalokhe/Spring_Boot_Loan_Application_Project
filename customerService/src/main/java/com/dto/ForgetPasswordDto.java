package com.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor	
public class ForgetPasswordDto {

	private String username;

	private String email;

	private String newpassword;

	private String confirmpassword;

}
