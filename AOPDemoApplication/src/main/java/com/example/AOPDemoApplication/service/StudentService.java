package com.example.AOPDemoApplication.service;

import com.example.AOPDemoApplication.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public String createStudent(Student student) {
        System.out.println(
                "Actual createStudent() method executed"
        );
        return "Student created successfully";

    }

    public String getStudent(String s) {
        System.out.println(
                "Actual createStudent() method executed"
        );
        return "Studnet details";
    }
}
