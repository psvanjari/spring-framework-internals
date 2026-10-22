package com.example.CollegeManagementSystem.repository;

import com.example.CollegeManagementSystem.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College,Long> {
}
