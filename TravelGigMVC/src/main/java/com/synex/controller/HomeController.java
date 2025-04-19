package com.synex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.synex.model.Address;
import com.synex.model.User;
import com.synex.model.UserFormWrapper;

@Controller
public class HomeController {

	@GetMapping("/userForm")
	public String showForm(Model model) {
		model.addAttribute("user", new User());
		return "userForm"; // refers to userForm.jsp
	}

	@PostMapping("/submitUser")
	public String submitForm(@ModelAttribute("user") User user, Model model) {
		// Do something with the user data
		model.addAttribute("message", "User submitted successfully!");
		return "userResult"; // refers to userResult.jsp
	}

	@GetMapping("/form")
	public String showForm2(Model model) {
		model.addAttribute("formWrapper", new UserFormWrapper());
		return "userForm2";
	}

	@PostMapping("/submitForm")
	public String handleForm(@ModelAttribute("formWrapper") UserFormWrapper formWrapper) {
		User user = formWrapper.getUser();
		Address address = formWrapper.getAddress();

		// Process them
		System.out.println("Name: " + user.getName());
		System.out.println("City: " + address.getCity());

		return "userResult2";
	}

}
