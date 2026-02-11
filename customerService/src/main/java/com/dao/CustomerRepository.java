package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public interface CustomerRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	User findById(int id);

	User findByUsername(String username);

	User findByEmailAndUsername(String email, String username);
}
