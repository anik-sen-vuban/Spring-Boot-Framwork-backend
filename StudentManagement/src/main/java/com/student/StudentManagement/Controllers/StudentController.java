package com.student.StudentManagement.Controllers;

import com.student.StudentManagement.Entities.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.student.StudentManagement.Services.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //Get all students
    @GetMapping("/get")
    public ResponseEntity</*Student*/?> getAllStudent(){
        List<Student> respStudentList = studentService.getAllStudent();
        if(respStudentList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("List is Empty");
        }
        return ResponseEntity.status(HttpStatus.OK).body(respStudentList);
    }

    //Get a student by id
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id){
        Optional<Student> respStudent = studentService.getStudent(id);
        if(respStudent.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(respStudent);
    }

    //Create a student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student reqStudent){
        Student respStudent = studentService.createStudent(reqStudent);

        return ResponseEntity.status(HttpStatus.CREATED).body(respStudent);
    }

    //Update a student info
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student reqStudent){
        Optional<Student> respStudent = studentService.updateStudent(id, reqStudent);

        if(respStudent.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(respStudent);
    }

    //Hard delete a Student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        boolean respStudent = studentService.deleteStudent(id);
        if(!respStudent){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted sucessfully");
    }

    //Soft delete a student
    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id){
        boolean resqStudent = studentService.softDeleteStudent(id);
        if(!resqStudent){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + "Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Student softly deleted sucesssfully");
    }

}
