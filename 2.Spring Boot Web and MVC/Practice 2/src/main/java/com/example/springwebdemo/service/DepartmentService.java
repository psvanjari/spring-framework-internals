package com.example.springwebdemo.service;

import com.example.springwebdemo.dto.DepartmentRequest;
import com.example.springwebdemo.entity.Department;
import com.example.springwebdemo.exception.DepartmentNotFoundException;
import com.example.springwebdemo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Department findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    public Department create(DepartmentRequest request) {
        Department department = new Department();
        department.setTitle(request.getTitle().trim());
        department.setIsActive(request.getIsActive());
        department.setCreatedAt(LocalDateTime.now());
        return repository.save(department);
    }

    public Department update(Long id, DepartmentRequest request) {
        Department department = findById(id);
        department.setTitle(request.getTitle().trim());
        department.setIsActive(request.getIsActive());
        return repository.save(department);
    }

    public void delete(Long id) {
        Department department = findById(id);
        repository.delete(department);
    }
}
