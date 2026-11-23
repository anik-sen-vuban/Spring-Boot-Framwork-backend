package com.anik.JPARelationshipsDemoApplication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anik.JPARelationshipsDemoApplication.model.Department;
import com.anik.JPARelationshipsDemoApplication.service.DepartmentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping ("/api/department")
public class DepartmentController {
    DepartmentService departmentService;

    public DepartmentController(
        DepartmentService departmentService
    ){
        this.departmentService = departmentService;
    }

    @PostMapping()
    public ResponseEntity<String> createDepartment(
        @RequestBody Department department) {
        departmentService.createDepartment(department);
        
        return ResponseEntity.ok("DONE");
    }
    
    @PostMapping("/withStudent")
    public ResponseEntity<String> createDepartment(
        @RequestBody Department department,
        @RequestParam String studentName
    ) {
        departmentService.createDepartment(department, studentName);
        
        return ResponseEntity.ok("DONE");
    }
    
}
