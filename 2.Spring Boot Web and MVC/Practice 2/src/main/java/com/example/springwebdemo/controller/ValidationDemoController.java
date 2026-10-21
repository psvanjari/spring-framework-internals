package com.example.springwebdemo.controller;

import com.example.springwebdemo.dto.ValidationDemoRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/validation-demo")
public class ValidationDemoController {

    @PostMapping
    public Map<String, String> validate(@Valid @RequestBody ValidationDemoRequest request) {
        return Map.of("message", "Number and password are valid");
    }
}
