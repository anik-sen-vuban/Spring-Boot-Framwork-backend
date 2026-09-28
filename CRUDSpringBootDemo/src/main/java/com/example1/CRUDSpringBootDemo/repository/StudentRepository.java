package com.example1.CRUDSpringBootDemo.repository;

import com.example1.CRUDSpringBootDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component
//@Repository // it let a developer better understanding that it is a repository, it also uses @Component
public interface StudentRepository extends JpaRepository<Student, Long> {

//    public Student saveStudent(Student studentReq){
        //save to db
//        System.out.println("Inside Student Repository");

//        Student dummyStudent = new Student();
//        dummyStudent.setName("DummyStudent");
//        dummyStudent.setEmail("dummy@gmail.com");
//        dummyStudent.setAge(23);
//        dummyStudent.setSubject("Spring Boot");

//        System.out.println("Exit Student Repository");
//        return dummyStudent;
//        return null;
//    }

    // here, all methods are implemented during runtime by the compiler

}
