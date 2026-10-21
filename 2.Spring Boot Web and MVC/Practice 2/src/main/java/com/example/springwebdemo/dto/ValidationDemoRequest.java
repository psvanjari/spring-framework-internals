package com.example.springwebdemo.dto;

import com.example.springwebdemo.validation.PrimeNumber;
import com.example.springwebdemo.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ValidationDemoRequest {

    @NotNull(message = "number is required")
    @PrimeNumber
    private Integer number;

    @NotBlank(message = "password is required")
    @ValidPassword
    private String password;

    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
