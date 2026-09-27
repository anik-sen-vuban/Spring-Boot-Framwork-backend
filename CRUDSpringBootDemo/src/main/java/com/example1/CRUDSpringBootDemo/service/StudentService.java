package com.example1.CRUDSpringBootDemo.service;

import com.example1.CRUDSpringBootDemo.entity.Student;
import com.example1.CRUDSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service //is used to better understanding for other developer that here have business logic, is also use @Component annotation
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createdStudent(Student studentReq){
        //business logic
        //handover to repository to store to db
        System.out.println("Inside Student Service");
        Student studentResp = studentRepository.saveStudent(studentReq);
        System.out.println("Exit Student Service");
        return studentResp;
    }
}
