package com.synex.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.synex.domain.Role;
import com.synex.domain.User;
import com.synex.service.RoleService;
import com.synex.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@SessionAttributes("user")
public class UserController {
	
	@Autowired UserService userService;
	@Autowired RoleService roleService;
	
	@GetMapping("/fetchUser")
	public String fetchUserPage(Principal principal) {
		return "fetchUser";
	}

	@GetMapping(value = "/login")
	public String login(@RequestParam(required = false) String logout, @RequestParam(required = false) String error,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		String message = "";
		if (error != null) {
			message = "Invalid Credentials";
		}
		if (logout != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
			}
			message = "Logout";
			return "login";
		}
		model.addAttribute("Message", message);
		return "login";

	}

	@GetMapping(value = "/accessDeniedPage")
	public String accessDenied(Principal principal, Model model) {
		String message = principal.getName() + ", Unauthorised access";
		model.addAttribute("Message", message);
		return "accessDeniedPage";

	}

	@PostMapping(value = "/signup")
	public String signup(@RequestParam String userEmail, @RequestParam String userName, @RequestParam String password) {
		User user = new User();	
		user.setUserName(userName);
		user.setEmail(userEmail);
		user.setUserPassword(password);
		Set<Role> role = roleService.getDefaultRole();
		user.setRoles(role);
		userService.save(user);
		return "login";

	}
	
	@GetMapping("/register")
	public String register() {
		return "signup";
	}
	
	@GetMapping(value = "/user/{username}")
	@ResponseBody
	public String getUserByUsername(@PathVariable String username) {
		return userService.findByUserName(username).getEmail();

	}
	
	@GetMapping("/userProfile")
	public String userProfile(Principal principal) {
		
		if(principal != null) {
			System.out.println("Logged In User Is: "+principal.getName());
			return "userProfile";
		}
		
		return "redirect:NotFound";
	}


//	@Bean
//	public BCryptPasswordEncoder bCryptpeasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
