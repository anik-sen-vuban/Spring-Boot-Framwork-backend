package com.anik.JPARelationshipsDemoApplication.service;

import org.springframework.stereotype.Service;

import com.anik.JPARelationshipsDemoApplication.model.Department;
import com.anik.JPARelationshipsDemoApplication.model.Student;
import com.anik.JPARelationshipsDemoApplication.repository.DepartmentRepository;
import com.anik.JPARelationshipsDemoApplication.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service 
public class StudentService {
    StudentRepository studentRepository;
    DepartmentRepository departmentRepository;

    public StudentService(
        StudentRepository studentRepository,
        DepartmentRepository departmentRepository
    ){
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional 
    public void createStudent(Student student, Long deptId){
        Department department = departmentRepository.getDepartmentById(deptId);
        student.setDepartment(department);
        department.getStudents().add(student);

        studentRepository.save(student);
    }

    @Transactional 
    public void createStudent(Student student, String deptName){
        Department department = new Department();
        department.setName(deptName);

        student.setDepartment(department);
        department.getStudents().add(student);

        departmentRepository.save(department);
        studentRepository.save(student);
    }
}
