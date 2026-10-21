package com.example.springwebdemo.repository;

import com.example.springwebdemo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByTitleIgnoreCase(String title);
}
