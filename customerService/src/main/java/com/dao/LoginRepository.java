package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {
	
	Login findByEmail(String email);
	
}
