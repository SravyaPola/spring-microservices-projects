package com.synex.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.synex.domain.Role;
import com.synex.domain.User;
import com.synex.model.LoginDto;
import com.synex.model.RegisterDto;
import com.synex.repository.RoleRepository;
import com.synex.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public String register(RegisterDto registerDto) {
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			return "User name is already exists!.";
		} else if (userRepository.existsByEmail(registerDto.getEmail())) {
			return "Email is already exists!.";
		} else {

			User user = new User();
			user.setUsername(registerDto.getUsername());
			user.setEmail(registerDto.getEmail());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(registerDto.getRole().toUpperCase());
			roles.add(userRole);
			user.setRoles(roles);
			userRepository.save(user);

			return "success";
		}
	}

	@Override
	public String login(LoginDto loginDto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "Login Successful";
	}

	@Override
	public Set<Role> getUserRoleByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user.getRoles();
	}
}
