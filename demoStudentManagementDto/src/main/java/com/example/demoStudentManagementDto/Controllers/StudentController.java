package com.example.demoStudentManagementDto.Controllers;

import com.example.demoStudentManagementDto.DataTransferObjects_DTOs.CreateStudentRequestDto;
import com.example.demoStudentManagementDto.DataTransferObjects_DTOs.CreateStudentResponseDto;
import com.example.demoStudentManagementDto.Services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDto> createStudent(@RequestBody CreateStudentRequestDto studentRequestDto) {
        CreateStudentResponseDto createStudentResponseDto = studentService.createStudent(studentRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createStudentResponseDto);
    }
}
