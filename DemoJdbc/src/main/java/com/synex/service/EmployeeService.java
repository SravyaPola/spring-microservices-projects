package com.synex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.dao.EmployeeDao;
import com.synex.model.Employee;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    // Method to add employees in batch
    public void addEmployeesInBatch(List<Employee> employees) {
        employeeDao.batchInsert(employees);
    }

    // Method to retrieve all employees
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }
}

