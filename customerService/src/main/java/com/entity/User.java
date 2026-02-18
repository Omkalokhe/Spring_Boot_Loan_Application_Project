package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String fname;

	@Column(nullable = false)
	private String lname;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String email;

	private String dob;

	private String mobileno;

	private String country;

	private String state;

	private String city;

	private String zipcode;

	private String createdby;

	private String createdon;

	private String updatedby;

	private String updatedon;

	private boolean status;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Login login;

	private String roleName;
}
