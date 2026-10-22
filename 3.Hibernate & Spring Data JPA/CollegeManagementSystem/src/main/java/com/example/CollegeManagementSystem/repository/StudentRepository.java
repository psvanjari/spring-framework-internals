package com.example.CollegeManagementSystem.repository;

import com.example.CollegeManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
