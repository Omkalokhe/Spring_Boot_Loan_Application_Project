package com.service;

import org.springframework.http.ResponseEntity;

import com.entity.Role;

public interface RoleService {
	
	public ResponseEntity<?> createRole(Role role);
	
	public ResponseEntity<?> getAllRole();
	
	public ResponseEntity<?> updateRole(Role role);
	
	public ResponseEntity<?> deleteRole(int id);
	
	public ResponseEntity<?> getRoleByName(String role);
	
	
}
