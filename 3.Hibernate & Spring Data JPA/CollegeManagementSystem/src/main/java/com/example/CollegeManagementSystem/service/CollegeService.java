package com.example.CollegeManagementSystem.service;

import com.example.CollegeManagementSystem.dto.Requests.*;
import com.example.CollegeManagementSystem.dto.Responses.*;
import com.example.CollegeManagementSystem.entity.*;
import com.example.CollegeManagementSystem.exception.ResourceNotFoundException;
import com.example.CollegeManagementSystem.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollegeService {
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;
    private final StudentProfileRepository profileRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

//    public Responses.CollegeResponse createCollege(
//            Requests.CollegeRequest request
//    ){
//        College college=new College(
//                request.name(),request.address()
//        );
//        return toCollege(colleges.save(college));
//    }
//
//    public Responses.DepartmentResponse createDepartment(
//            Requests.DepartmentRequest request
//    ){
//        Department department=new Department(request.name());
//
//        return toDepartment(departments.save(department));
//    }
//
//    public Responses.StudentResponse createResponse(
//            Requests.StudentRequest request
//    ){
//        Student student=new Student(request.name(),request.email());
//
//        return toStudent(students.save(student));
//    }
//
//    public Responses.ProfileResponse createProfile(
//            Requests.ProfileRequest request
//    ){
//        StudentProfile studentProfile=new StudentProfile(
//                request.phone(),request.address()
//        );
//
//        return toProfile(profiles.save(studentProfile));
//    }
//
//    public Responses.CourseResponse createCourse(
//            Requests.CourseRequest request
//    ){
//        Course course=new Course(
//                request.title(),
//                request.courseCode()
//        );
//        return  toCourse(courses.save(course));
//    }

    public CollegeResponse createCollege(CollegeRequest request){
        College college=modelMapper.map(request,College.class);
        College saved=collegeRepository.save(college);
        return modelMapper.map(saved,CollegeResponse.class);
    }

    @Transactional
    public List<CollegeResponse> getAllColleges(){
        return collegeRepository.findAll()
                .stream()
                .map(
                        c->modelMapper.map(c,CollegeResponse.class)
                )
                .toList();
    }

    public DepartmentResponse createDepartment(
            DepartmentRequest request) {

        Department department =
                modelMapper.map(request, Department.class);

        Department savedDepartment =
                departmentRepository.save(department);

        return modelMapper.map(
                savedDepartment, DepartmentResponse.class);
    }

    public DepartmentResponse assignDepartmentToCollege(
            Long departmentId, Long collegeId) {

        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found: " + departmentId));

        College college = collegeRepository
                .findById(collegeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "College not found: " + collegeId));

        department.setCollege(college);

        Department savedDepartment =
                departmentRepository.save(department);

        return modelMapper.map(
                savedDepartment, DepartmentResponse.class);
    }

    @Transactional
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(department ->
                        modelMapper.map(
                                department, DepartmentResponse.class))
                .toList();
    }

    public StudentResponse createStudent(StudentRequest request) {
        Student student = modelMapper.map(request, Student.class);

        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, StudentResponse.class);
    }

    public StudentResponse assignStudentToDepartment(
            Long studentId, Long departmentId) {

        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found: " + studentId));

        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found: " + departmentId));

        student.setDepartment(department);

        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, StudentResponse.class);
    }

    @Transactional
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student ->
                        modelMapper.map(student, StudentResponse.class))
                .toList();
    }

    public ProfileResponse createProfile(ProfileRequest request) {
        StudentProfile profile =
                modelMapper.map(request, StudentProfile.class);

        StudentProfile savedProfile =
                profileRepository.save(profile);

        return modelMapper.map(savedProfile, ProfileResponse.class);
    }

    public ProfileResponse assignProfileToStudent(
            Long profileId, Long studentId) {

        StudentProfile profile = profileRepository
                .findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profile not found: " + profileId));

        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found: " + studentId));

        profile.setStudent(student);

        StudentProfile savedProfile =
                profileRepository.save(profile);

        return modelMapper.map(savedProfile, ProfileResponse.class);
    }

    public CourseResponse createCourse(CourseRequest request) {
        Course course = modelMapper.map(request, Course.class);

        Course savedCourse = courseRepository.save(course);

        return modelMapper.map(savedCourse, CourseResponse.class);
    }

    @Transactional
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(course ->
                        modelMapper.map(course, CourseResponse.class))
                .toList();
    }

    public StudentResponse assignCourseToStudent(
            Long studentId, Long courseId) {

        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found: " + studentId));

        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found: " + courseId));

        // Student owns the ManyToMany relationship.
        student.getCourses().add(course);

        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, StudentResponse.class);
    }

}
