package com.synex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.synex.model.Employee;

@RestController
public class EmpController {

	@GetMapping("/getEmp")
	public Employee getEmp() {
		return new Employee("Ankit","Arora",42);
	}
	
}
