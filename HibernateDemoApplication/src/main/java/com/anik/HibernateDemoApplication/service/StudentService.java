package com.anik.HibernateDemoApplication.service;

import org.springframework.stereotype.Service;

import com.anik.HibernateDemoApplication.model.Student;
import com.anik.HibernateDemoApplication.model.StudentStatus;
import com.anik.HibernateDemoApplication.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    StudentRepository studentRepository;

    public StudentService(
        StudentRepository studentRepository
    ){
        this.studentRepository = studentRepository;
    }

    @Transactional 
    public void createStudent(Student student){
        student.setActive(true);
        student.setStatus(StudentStatus.ACTIVE);
        studentRepository.save(student);
    }

    @Transactional
    public Student getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void updateStudent(Student studentReq, Long id) {
        Student student1 = studentRepository.findById(id);

        if(student1 == null) {
            throw new RuntimeException("Student not found");
        }
        student1.setActive(true);
        student1.setStatus(StudentStatus.ACTIVE);
        student1.setName(studentReq.getName());
        student1.setAge(studentReq.getAge());
        student1.setEmail(studentReq.getEmail());
        student1.setSkills(studentReq.getSkills());
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id);

        if(student == null) {
            throw new RuntimeException("Student not found");
        }
        // student.setActive(false);
        // student.setStatus(StudentStatus.INACTIVE);
        // studentRepository.save(student);
        studentRepository.remove(student);
    }

}
