package com.example.CollegeManagementSystem.repository;

import com.example.CollegeManagementSystem.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile,Long> {
}
