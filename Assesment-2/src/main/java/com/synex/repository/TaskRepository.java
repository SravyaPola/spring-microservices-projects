package com.synex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.synex.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
