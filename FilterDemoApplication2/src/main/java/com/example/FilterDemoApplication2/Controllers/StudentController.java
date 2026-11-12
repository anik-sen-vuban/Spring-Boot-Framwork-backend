package com.example.FilterDemoApplication2.Controllers;

import com.example.FilterDemoApplication2.Dtos.StudentRequestDto;
import com.example.FilterDemoApplication2.Dtos.StudentResponseDto;
import com.example.FilterDemoApplication2.Services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody StudentRequestDto studentReq){
        StudentResponseDto studentResp = studentService.createStudent(studentReq);

        return ResponseEntity.ok(studentResp);
    }
}
