package com.example.springwebdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DepartmentRequest {

    @NotBlank(message = "title is required")
    @Size(min = 2, max = 80, message = "title must contain 2 to 80 characters")
    private String title;

    @NotNull(message = "isActive is required")
    private Boolean isActive;

    public DepartmentRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
