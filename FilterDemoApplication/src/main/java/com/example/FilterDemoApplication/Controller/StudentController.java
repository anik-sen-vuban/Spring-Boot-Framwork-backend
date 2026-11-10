package com.example.FilterDemoApplication.Controller;

import com.example.FilterDemoApplication.Dto.StudentRequestDto;
import com.example.FilterDemoApplication.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody StudentRequestDto studentReq){
        studentService.createStudent(studentReq);
        return ResponseEntity.ok("DONE");
    }
}
