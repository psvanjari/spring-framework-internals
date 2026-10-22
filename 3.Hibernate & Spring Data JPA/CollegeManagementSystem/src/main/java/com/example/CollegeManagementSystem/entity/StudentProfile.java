package com.example.CollegeManagementSystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String phone;

    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id",nullable = false,unique = true)
    private Student student;

    public StudentProfile(String phone, String address) {
        this.phone = phone;
        this.address = address;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
