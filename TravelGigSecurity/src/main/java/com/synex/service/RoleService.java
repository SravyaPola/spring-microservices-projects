package com.synex.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.synex.domain.Role;
import com.synex.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Set<Role> getDefaultRole() {
		Role role = roleRepository.findById(2).orElse(null);
		Set<Role> setRole = new HashSet<>();
		setRole.add(role);
		return setRole;
	}

}