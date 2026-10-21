package com.example.springwebdemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department title must not be blank")
    @Size(min = 2, max = 80, message = "Title must contain 2 to 80 characters")
    @Column(nullable = false, length = 80)
    private String title;

    @NotNull(message = "isActive must be provided")
    @Column(nullable = false)
    private Boolean isActive;

    @NotNull
    @PastOrPresent(message = "createdAt cannot be in the future")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Demonstration field for @AssertFalse: archived must be false for this API's create/update model.
    @AssertFalse(message = "A department cannot be archived through this endpoint")
    private boolean archived = false;

    public Department() {}

    public Department(String title, Boolean isActive, LocalDateTime createdAt) {
        this.title = title;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    @PrePersist
    void beforeInsert() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (isActive == null) {
            isActive = true;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean active) { isActive = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public boolean isArchived() { return archived; }
    public void setArchived(boolean archived) { this.archived = archived; }
}
