package com.example1.CRUDSpringBootDemo.repository;

import com.example1.CRUDSpringBootDemo.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentRepository {

    public Student saveStudent(Student studentReq){
        //save to db
        System.out.println("Inside Student Repository");

        Student dummyStudent = new Student();
        dummyStudent.setName("DummyStudent");
        dummyStudent.setEmail("dummy@gmail.com");
        dummyStudent.setAge(23);
        dummyStudent.setSubject("Spring Boot");

        System.out.println("Exit Student Repository");
        return dummyStudent;
    }
}
