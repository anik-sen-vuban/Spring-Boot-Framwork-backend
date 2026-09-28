package com.example1.CRUDSpringBootDemo.service;

import com.example1.CRUDSpringBootDemo.entity.Student;
import com.example1.CRUDSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //is used to better understanding for other developer that here have business logic, is also use @Component annotation
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createdStudent(Student studentReq){
        //business logic
        //handover to repository to store to db
//        System.out.println("Inside Student Service");
        Student studentResp = studentRepository.save(studentReq);
//        System.out.println("Exit Student Service");
        return studentResp;
    }

    public /*Student*/ Optional<Student> getStudentById(Long id){
        Optional<Student> student = studentRepository.findById(id);

//        if(student.isPresent()){
//            return student.get();
//        }
//
//        return null;
        return student;
    }

    public List<Student> getAllStudent(){
        List<Student> studentList = studentRepository.findAll();

        return studentList;
    }

//    public Student updateStudent(Long id, Student studentReq){
//        boolean exists = studentRepository.existsById(id);
//        if(!exists){
//            return null;
//        }
//        Student studentResp = studentRepository.save(studentReq);
//        return studentResp;
//    }

    public /*Student*/ Optional<Student> updateStudent(Long id, Student studentReq){
        Optional<Student> studentResp = studentRepository.findById(id);

//        if(student.isPresent()){
//            return student.get();
//        }
//
//        return null;
        if(studentResp.isPresent()){
            studentRepository.save(studentReq);
            return studentResp;
        }
        return null;
    }
}
