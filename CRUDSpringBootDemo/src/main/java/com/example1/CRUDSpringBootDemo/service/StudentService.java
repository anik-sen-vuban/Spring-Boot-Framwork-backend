package com.example1.CRUDSpringBootDemo.service;

import com.example1.CRUDSpringBootDemo.entity.Student;
import com.example1.CRUDSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
        studentReq.setIsDeleted(false);
        Student studentResp = studentRepository.save(studentReq);
//        System.out.println("Exit Student Service");
        return studentResp;
    }

    public /*Student*/ Optional<Student> getStudentById(Long id){
//        Optional<Student> student = studentRepository.findById(id);

        // when soft delete is present
        Optional<Student> student = studentRepository.findByIdAndIsDeletedFalse(id);

//        if(student.isPresent()){
//            return student.get();
//        }
//
//        return null;
        return student;
    }

    public List<Student> getAllStudent(){
//        List<Student> studentList = studentRepository.findAll();

        //when soft delete is present
        List<Student> studentList = studentRepository.findAllByIsDeletedFalse();

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
//        Optional<Student> existingStudent = studentRepository.findById(id);

        // When soft delete is present
        Optional<Student> existingStudent = studentRepository.findByIdAndIsDeletedFalse(id);
//        if(student.isPresent()){
//            return student.get();
//        }
//
//        return null;
//        if(studentResp.isPresent()){
//            studentRepository.save(studentReq);
//            return studentResp;
//        }
//        return null;

        if(existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setIsDeleted(false);
        studentRepository.save(studentToSave);
        return existingStudent;
    }

    public boolean deleteStudent(Long id){
        boolean isDeleted = studentRepository.existsById(id);
        if(isDeleted){
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteStudentSoftly(Long id) {
        Optional<Student> existStudent = studentRepository.findByIdAndIsDeletedFalse(id);

        if(existStudent.isEmpty()){
            return false;
        }

        Student studentToSave = existStudent.get();
        studentToSave.setIsDeleted(true);
        studentRepository.save(studentToSave);
        return true;
    }
}
