package com.example.CollegeManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "colleges")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false,unique = true)
    private String name;

    private String address;

    @OneToMany(mappedBy = "college")
    private List<Department> departments=new ArrayList<>();

    public College(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public List<Department> getDepartments() {
        return departments;
    }

}
