package com.example.FilterDemoApplication.Service;

import com.example.FilterDemoApplication.Dto.StudentRequestDto;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public void createStudent(StudentRequestDto studentReq){
        System.out.println("Student Created");
        System.out.println(studentReq.getName());
        System.out.println(studentReq.getEmail());
    }
}
