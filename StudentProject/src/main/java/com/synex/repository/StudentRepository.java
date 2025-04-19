package com.synex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.synex.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
}
