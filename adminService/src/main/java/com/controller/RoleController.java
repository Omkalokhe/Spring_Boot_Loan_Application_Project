package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Role;
import com.service.RoleService;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping(value = "/api/admin/createRole")
	public ResponseEntity<?> createRole(@RequestBody Role role) {
		ResponseEntity<?> response = roleService.createRole(role);
		return response;
	}

	@GetMapping(value = "/api/admin/getRole")
	public ResponseEntity<?> getRole() {
		ResponseEntity<?> response = roleService.getAllRole();
		return response;
	}

	@GetMapping(value = "/api/admin/getRoleByName/{role}")
	public ResponseEntity<?> getRoleByName(@PathVariable("role") String role) {
		ResponseEntity<?> response = roleService.getRoleByName(role);
		return response;
	}

	@PutMapping(value = "/api/admin/updateRole")
	public ResponseEntity<?> updateRole(@RequestBody Role role) {
		ResponseEntity<?> response = roleService.updateRole(role);
		return response;
	}

	@DeleteMapping(value = "/api/admin/deleteRole/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable("id") int id) {
		ResponseEntity<?> response = roleService.deleteRole(id);
		return response;
	}

}
