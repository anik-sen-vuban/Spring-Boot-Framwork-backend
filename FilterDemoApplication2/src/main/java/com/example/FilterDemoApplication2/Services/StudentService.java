package com.example.FilterDemoApplication2.Services;

import com.example.FilterDemoApplication2.Dtos.StudentRequestDto;
import com.example.FilterDemoApplication2.Dtos.StudentResponseDto;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public StudentResponseDto createStudent(StudentRequestDto studentReq){
        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setName(studentReq.getName());
        responseDto.setMessage("Student is saved successfully");

        return responseDto;
    }
}
