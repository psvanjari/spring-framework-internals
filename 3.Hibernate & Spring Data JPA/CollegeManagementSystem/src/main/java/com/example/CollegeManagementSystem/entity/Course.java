package com.example.CollegeManagementSystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String title;

    private String courseCode;

    public Course(String title, String courseCode) {
        this.title = title;
        this.courseCode = courseCode;
    }
}
