package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.synex.domain.Student;
import com.synex.service.StudentService;

@RestController
@RequestMapping("/api/students")
class StudentController {
	@Autowired
	private StudentService service;

	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student student) {
		return service.addStudent(student);
	}

	@GetMapping("/listStudents")
	public List<Student> getStudents() {
		return service.getAllStudents();
	}
}