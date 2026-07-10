package com.devsenior.alara.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.alara.dto.StudentRequest;
import com.devsenior.alara.dto.StudentResponse;
import com.devsenior.alara.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> save(
        @Valid
        @RequestBody
        StudentRequest request

    ){
        ResponseEntity.status(HttpStatus.CREATED);
        return ResponseEntity.ok(service.save(request));
    }
}
