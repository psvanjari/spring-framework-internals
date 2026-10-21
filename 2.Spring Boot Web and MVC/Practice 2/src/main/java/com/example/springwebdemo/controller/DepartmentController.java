package com.example.springwebdemo.controller;

import com.example.springwebdemo.dto.DepartmentRequest;
import com.example.springwebdemo.dto.DepartmentUpdateRequest;
import com.example.springwebdemo.entity.Department;
import com.example.springwebdemo.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Department> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department create(@Valid @RequestBody DepartmentRequest request) {
        return service.create(request);
    }

    // Matches the homework sheet: PUT /departments, with id in the JSON body.
    @PutMapping
    public Department updateFromBody(@Valid @RequestBody DepartmentUpdateRequest request) {
        DepartmentRequest update = new DepartmentRequest();
        update.setTitle(request.getTitle());
        update.setIsActive(request.getIsActive());
        return service.update(request.getId(), update);
    }

    // Conventional REST alternative: id is supplied in the URL.
    @PutMapping("/{id}")
    public Department update(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
