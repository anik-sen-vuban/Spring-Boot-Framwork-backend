package com.example.AOPDemoApplication.controller;

import com.example.AOPDemoApplication.dto.Student;
import com.example.AOPDemoApplication.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent(
            @RequestBody Student student
    ){

        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping
    public ResponseEntity<String> getStudent(){
        String s = "anik";
        return ResponseEntity.ok(studentService.getStudent(s));
    }
}
