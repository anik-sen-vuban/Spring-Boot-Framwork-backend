package com.student.StudentManagement.Services;

import com.student.StudentManagement.Entities.Student;
import com.student.StudentManagement.Repositories.StudentRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    //Get all students
    public List<Student> getAllStudent(){
        List<Student> reqStudentList = studentRepository.findAll();
        return reqStudentList;
    }

    //Get a student
    public Optional<Student> getStudent(Long id){
        Optional<Student> reqStudent = studentRepository.findById(id);

        if(reqStudent.isEmpty()){
            return Optional.empty();
        }
        return reqStudent;
    }

    //Create a student
    public Student createStudent(Student reqstudent){
        Student respStudent = studentRepository.save(reqstudent);

        return respStudent;
    }

    //Update a student
    public Student updateStudent(Long id, Student student){
        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isEmpty()){
            return null;
        }
        existingStudent.get().setAge(student.getAge());
        existingStudent.get().setName(student.getName());
        existingStudent.get().setEmail(student.getEmail());
        existingStudent.get().setSubject(student.getSubject());

        studentRepository.save(existingStudent.get());
        return existingStudent.get();
    }

}
