package com.example.CollegeManagementSystem.controller;

import com.example.CollegeManagementSystem.dto.Requests.*;
import com.example.CollegeManagementSystem.dto.Responses.*;
import com.example.CollegeManagementSystem.service.CollegeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CollegeController {
    private final CollegeService collegeService;

    @PostMapping("/colleges")
    @ResponseStatus(HttpStatus.CREATED)
    public CollegeResponse createCollege(
            @Valid @RequestBody CollegeRequest request) {

        return collegeService.createCollege(request);
    }

    @GetMapping("/colleges")
    public List<CollegeResponse> getAllColleges() {
        return collegeService.getAllColleges();
    }



    @PostMapping("/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse createDepartment(
            @Valid @RequestBody DepartmentRequest request) {

        return collegeService.createDepartment(request);
    }

    @GetMapping("/departments")
    public List<DepartmentResponse> getAllDepartments() {
        return collegeService.getAllDepartments();
    }

    @PutMapping("/departments/{departmentId}/college/{collegeId}")
    public DepartmentResponse assignDepartmentToCollege(
            @PathVariable Long departmentId,
            @PathVariable Long collegeId) {

        return collegeService.assignDepartmentToCollege(
                departmentId, collegeId);
    }


    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse createStudent(
            @Valid @RequestBody StudentRequest request) {

        return collegeService.createStudent(request);
    }

    @GetMapping("/students")
    public List<StudentResponse> getAllStudents() {
        return collegeService.getAllStudents();
    }

    @PutMapping("/students/{studentId}/department/{departmentId}")
    public StudentResponse assignStudentToDepartment(
            @PathVariable Long studentId,
            @PathVariable Long departmentId) {

        return collegeService.assignStudentToDepartment(
                studentId, departmentId);
    }


    @PostMapping("/profiles")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse createProfile(
            @Valid @RequestBody ProfileRequest request) {

        return collegeService.createProfile(request);
    }

    @PutMapping("/profiles/{profileId}/student/{studentId}")
    public ProfileResponse assignProfileToStudent(
            @PathVariable Long profileId,
            @PathVariable Long studentId) {

        return collegeService.assignProfileToStudent(
                profileId, studentId);
    }


    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(
            @Valid @RequestBody CourseRequest request) {

        return collegeService.createCourse(request);
    }

    @GetMapping("/courses")
    public List<CourseResponse> getAllCourses() {
        return collegeService.getAllCourses();
    }

    @PutMapping("/students/{studentId}/courses/{courseId}")
    public StudentResponse assignCourseToStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        return collegeService.assignCourseToStudent(
                studentId, courseId);
    }

}
