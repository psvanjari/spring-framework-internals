package com.example.CollegeManagementSystem.repository;

import com.example.CollegeManagementSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
