package com.example.demoStudentManagementDto.Controllers;

import com.example.demoStudentManagementDto.DataTransferObjects_DTOs.*;
import com.example.demoStudentManagementDto.Services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @Value("${app.welcome.message}")
    private String welcomeMessage;

    @GetMapping("/welcome")
    public String getWelcomeMessage(){
        return welcomeMessage;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDto> createStudent(@Valid @RequestBody CreateStudentRequestDto studentRequestDto) {
        CreateStudentResponseDto createStudentResponseDto = studentService.createStudent(studentRequestDto);

        URI location = URI.create("/api/students/create" + createStudentResponseDto.getStudentInfoDto().getId());

//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(createStudentResponseDto);
        return ResponseEntity
                .created(location)
                .body(createStudentResponseDto);
    }

    @GetMapping("/get")
    public ResponseEntity<List<CreateStudentResponseDTOforAll>> getAllStudent(){
        List<CreateStudentResponseDTOforAll> studentList = studentService.getAllStudent();

        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudnet(@PathVariable Long id){
        CreateStudentResponseDto studentResp = studentService.getStudent(id);

        return ResponseEntity.status(HttpStatus.OK).body(studentResp);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStudentRequestDto studentReq){
        UpdateStudentResponseDto studentResp = studentService
                .updateStudent(id, studentReq);

        return ResponseEntity.status(HttpStatus.OK).body(studentResp);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity</*ApiRespDtoForDelete*/Void> deleteStudent(@PathVariable Long id){
//        ApiRespDtoForDelete respStudent = studentService.deleteStudent(id);

//        if(!existingStudent){
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(respStudent);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();

    }
}
