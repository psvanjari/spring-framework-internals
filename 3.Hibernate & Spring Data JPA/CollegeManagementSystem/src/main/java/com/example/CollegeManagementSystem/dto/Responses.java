package com.example.CollegeManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Responses {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CollegeResponse {
        private Long id;
        private String name;
        private String address;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentResponse {
        private Long id;
        private String name;
        private Long collegeId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentResponse {
        private Long id;
        private String name;
        private String email;
        private Long departmentId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProfileResponse {
        private Long id;
        private String phone;
        private String address;
        private Long studentId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseResponse {
        private Long id;
        private String title;
        private String courseCode;
    }
}
