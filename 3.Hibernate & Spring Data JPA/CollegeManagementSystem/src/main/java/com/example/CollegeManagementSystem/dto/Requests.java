package com.example.CollegeManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Requests {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CollegeRequest {
        private String name;
        private String address;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentRequest {
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentRequest {
        private String name;
        private String email;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProfileRequest {
        private String phone;
        private String address;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseRequest {
        private String title;
        private String courseCode;
    }
}
