package com.anik.JPARelationshipsDemoApplication.service;

import org.springframework.stereotype.Service;

import com.anik.JPARelationshipsDemoApplication.model.Department;
import com.anik.JPARelationshipsDemoApplication.model.Student;
import com.anik.JPARelationshipsDemoApplication.repository.DepartmentRepository;
import com.anik.JPARelationshipsDemoApplication.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service 
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private StudentRepository studentRepository;

    public DepartmentService(
        DepartmentRepository departmentRepository,
        StudentRepository studentRepository
    ){
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional 
    public void createDepartment(Department department){
        departmentRepository.save(department);
    }

    @Transactional 
    public void createDepartment(
        Department department,
        String studentName
    ){
        Student student = new Student();

        student.setName(studentName);
        student.setDepartment(department);

        department.getStudents().add(student);

        studentRepository.save(student);
        departmentRepository.save(department);
    }
}
