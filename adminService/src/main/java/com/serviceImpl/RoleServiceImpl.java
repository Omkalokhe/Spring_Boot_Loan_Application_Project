package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.RoleRepository;
import com.entity.Role;
import com.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public ResponseEntity<?> createRole(Role role) {
		if (role != null) {
			roleRepository.save(role);
			return new ResponseEntity<String>("Role Created Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Role is Not Created Successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllRole() {
		List<Role> allRole = roleRepository.findAll();
		return new ResponseEntity<List<Role>>(allRole, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateRole(Role role) {
		Role role2 = roleRepository.findById(role.getId()).get();
		if (role != null && role2 != null) {
			role2.setRolename(role.getRolename());
			roleRepository.save(role2);
			return new ResponseEntity<String>("Role Updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Role Is Not Updated Successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteRole(int id) {
		Role role = roleRepository.findById(id).get();
		if (role != null) {
			roleRepository.delete(role);
			return new ResponseEntity<String>("Role Deleted Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Role With This ID Is not Present", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getRoleByName(String role) {
		Role role1 = roleRepository.findByRolename(role);
		if (role1 != null) {
			return new ResponseEntity<Role>(role1, HttpStatus.OK);
		}

		return new ResponseEntity<String>("Role Is Unavilable", HttpStatus.OK);
	}

}
