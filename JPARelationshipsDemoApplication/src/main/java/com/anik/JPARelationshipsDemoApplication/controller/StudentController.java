package com.anik.JPARelationshipsDemoApplication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anik.JPARelationshipsDemoApplication.model.Student;
import com.anik.JPARelationshipsDemoApplication.service.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping ("/api/student")
public class StudentController {
    StudentService studentService;

    public StudentController (
        StudentService studentService
    ){
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<String> createStudent (
        @RequestBody Student student,
        @RequestParam Long deptId
    ) {
        studentService.createStudent(student, deptId);

        
        return ResponseEntity.ok("DONE");
    }

    @PostMapping("/withDepartment")
    public ResponseEntity<String> createStudent(
        @RequestBody Student student,
        @RequestParam String deptName
    ) {
        studentService.createStudent(student, deptName);
        
        return ResponseEntity.ok("Done");
    }
    
    
}
