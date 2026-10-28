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
//        List<Student> reqStudentList = studentRepository.findAll();
        List<Student> reqStudentList = studentRepository.findByIsDeletedFalse();
        return reqStudentList;
    }

    //Get a student
    public Optional<Student> getStudent(Long id){
//        Optional<Student> reqStudent = studentRepository.findById(id);
        Optional<Student> reqStudent = studentRepository.findByIdAndIsDeletedFalse(id);
        if(reqStudent.isEmpty()){
            return Optional.empty();
        }
        return reqStudent;
    }

    //Create a student
    public Student createStudent(Student reqstudent){
        reqstudent.setDeleted(false);
        Student respStudent = studentRepository.save(reqstudent);

        return respStudent;
    }

    //Update a student
    public Optional<Student> updateStudent(Long id, Student student){
//        Optional<Student> existingStudent = studentRepository.findById(id);
        Optional<Student> existingStudent = studentRepository.findByIdAndIsDeletedFalse(id);
        if(existingStudent.isEmpty()){
            return Optional.empty();
        }

        existingStudent.get().setDeleted(false);
        existingStudent.get().setAge(student.getAge());
        existingStudent.get().setName(student.getName());
        existingStudent.get().setEmail(student.getEmail());
        existingStudent.get().setSubject(student.getSubject());

        studentRepository.save(existingStudent.get());
        return existingStudent;
    }

    //Hard delete a stduent
    public boolean deleteStudent(Long id){
        boolean isExisting = studentRepository.existsById(id);
        if(!isExisting){
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    //Soft delete Student
    public boolean softDeleteStudent(Long id){
        Optional<Student> isExisting = studentRepository.findByIdAndIsDeletedFalse(id);
        if(isExisting.isEmpty()){
            return false;
        }
        Student existingStudent = isExisting.get();
        existingStudent.setDeleted(true);
        studentRepository.save(existingStudent);
        return true;
    }

}
