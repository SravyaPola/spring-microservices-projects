package com.synex.controller;

import org.springframework.stereotype.Controller;
import java.security.Principal;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.synex.domain.Role;
import com.synex.model.LoginDto;
import com.synex.model.RegisterDto;
import com.synex.model.RoleForm;
import com.synex.service.AuthService;

@Controller
public class MyController {
	//Problem Statement
//Create a Spring Boot project having a login, signup and default Home Page that has 2 buttons one is for the role USER & another for the role ADMIN
	//Also implement logout functionality.

	@Autowired
	private AuthService authService;

	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("registerDto", new RegisterDto());
		return "register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("registerDto") RegisterDto registerDto, Model model) throws Exception {
		String response = authService.register(registerDto);
		if(response == "success") {
			model.addAttribute("success", response);
			model.addAttribute("loginDto", new LoginDto());
			return "login";
		}else {
			model.addAttribute("error", response);
			return "register";
		}
	}

	@GetMapping("/login")
	public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
            model.addAttribute("errorMsg", "Invalid username or password.");
        }
		model.addAttribute("loginDto", new LoginDto());
		return "login";
	}

	@GetMapping("/home")
	public String homePage(Model model, Principal principal) {
		model.addAttribute("roleForm", new RoleForm());
		model.addAttribute("username", principal.getName());
		return "home";
	}

	@PostMapping("/home")
	public String handleRoleChoice(@ModelAttribute RoleForm roleForm, Principal principal, Model model) {

		String username = principal.getName();
		String selectedRole = roleForm.getRoleChoice();
		Set<Role> actualRoles = authService.getUserRoleByUsername(username);
		boolean hasRole = actualRoles.stream().anyMatch(role -> role.getName().equalsIgnoreCase(selectedRole));
		if (hasRole) {
			if ("ADMIN".equalsIgnoreCase(selectedRole))
				return "redirect:/admin/home";
			if ("USER".equalsIgnoreCase(selectedRole))
				return "redirect:/user/home";
		}
		model.addAttribute("error", "You are not authorized as " + selectedRole);
		model.addAttribute("username", username);
		return "home";
	}

	@GetMapping("/user/home")
	public String showUserHome(Model model, Principal principal) {
		model.addAttribute("name", principal.getName());
		return "user-dashboard";
	}

	@GetMapping("/admin/home")
	public String showAdminHome(Model model, Principal principal) {
		model.addAttribute("name", principal.getName());
		return "admin-dashboard";
	}

	@PostMapping("/logout-success")
	public String logoutPage() {
		return "logout";
	}
}
