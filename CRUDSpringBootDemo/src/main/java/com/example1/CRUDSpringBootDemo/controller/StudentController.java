package com.example1.CRUDSpringBootDemo.controller;

import com.example1.CRUDSpringBootDemo.entity.Student;
import com.example1.CRUDSpringBootDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //create Student
//    @PostMapping("/create") // if we need an end point for a specific operation
//    which operation should be made for same api is depends on methods, make a new end point for a specific operation is not mandatory
    @PostMapping
//    public Student createStudent(@RequestBody Student student){ //Jackson library convert the JSON request into the Java object
//        //to see the JSON request it could be handled or not (terminal view)
//        System.out.println("Name: " + student.getName());
//        System.out.println("Email: " + student.getEmail());
//        System.out.println("Inside Student controller");
//        Student createdStudent = studentService.createdStudent(student);
//        System.out.println("Exit Student controller");
//        return createdStudent;
//    }
    public ResponseEntity<Student> createdStudent(@RequestBody Student student) {
        System.out.println("Inside Student Controller");
        Student createdStudent = studentService.createdStudent(student);
        System.out.println("Exit Student Controller");
//        return ResponseEntity.status(201).body(createdStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    //read student

    //update student

    //delete student
}
