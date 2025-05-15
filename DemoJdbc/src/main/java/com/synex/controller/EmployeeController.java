package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.synex.model.Employee;
import com.synex.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Endpoint to get all employees
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    // Endpoint to add employees in batch
    @PostMapping("/batch")
    public String addEmployees(@RequestBody List<Employee> employees) {
        employeeService.addEmployeesInBatch(employees);
        return "Employees added successfully";
    }
}

