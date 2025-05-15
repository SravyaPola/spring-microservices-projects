package com.synex.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synex.model.Employee;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.util.List;

@Repository
public class EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method for batch insert
    public void batchInsert(List<Employee> employees) {
        String sql = "INSERT INTO employee (id, name, role) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(java.sql.PreparedStatement ps, int i) throws java.sql.SQLException {
                Employee employee = employees.get(i);
                ps.setInt(1, employee.getId());
                ps.setString(2, employee.getName());
                ps.setString(3, employee.getRole());
            }

            @Override
            public int getBatchSize() {
                return employees.size();
            }
        });
    }

    // Method for fetching all employees
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, (rs, rowNum) -> 
            new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("role"))
        );
    }
}

