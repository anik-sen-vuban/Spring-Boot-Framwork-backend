package com.example1.CRUDSpringBootDemo.controller;

import com.example1.CRUDSpringBootDemo.entity.Student;
import com.example1.CRUDSpringBootDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //create Student
//    @PostMapping("/create") // if we need an end point for a specific operation
//    which operation should be made for same api is depends on methods, make a new end point for a specific operation is not mandatory
//    @PostMapping
//    public Student createStudent(@RequestBody Student student){ //Jackson library convert the JSON request into the Java object
//        //to see the JSON request it could be handled or not (terminal view)
//        System.out.println("Name: " + student.getName());
//        System.out.println("Email: " + student.getEmail());
//        System.out.println("Inside Student controller");
//        Student createdStudent = studentService.createdStudent(student);
//        System.out.println("Exit Student controller");
//        return createdStudent;
//    }
    @PostMapping
    public ResponseEntity<Student> createdStudent(@RequestBody Student student) {
//        System.out.println("Inside Student Controller");
        Student createdStudent = studentService.createdStudent(student);
//        System.out.println("Exit Student Controller");
//        return ResponseEntity.status(201).body(createdStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    //read a student by id, using @PathVariable like "/api/student/{id}", another one is parameterized like "/api/student?id={value}" or "/api/student?id={value}&name={value)"
    @GetMapping(/*"{id}"*/ params = "id")
    public ResponseEntity<Student> getStudent(/*@PathVariable*/ @RequestParam Long id) {
        Optional<Student> studentResp = studentService.getStudentById(id);

//        if(studentResp == null){
//            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(null); // returns response body = null
//            return ResponseEntity.notFound().build(); //returns empty response body
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(studentResp);
        if(studentResp.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(studentResp.get());
        }
        return ResponseEntity.notFound().build();
        //build() : now create the final object/response
    }

    //read all student
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> studentList = studentService.getAllStudent();

        if(studentList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }

    //update a student
//    @PutMapping("{id}")
//    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentReq){
//        Student studentResp = studentService.updateStudent(id, studentReq);
//
//        if(studentResp == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(studentResp);
//    }
    @PutMapping(/*"{id}"*/ params = "id")
    public ResponseEntity<Student> updateStudent(/*@PathVariable*/ @RequestParam Long id,
                                                 @RequestBody Student studentreq){
//        Student studentResp = studentService.updateStudent(id);
//
//        if(studentResp == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(studentResp);
        Optional<Student> studentResp = studentService.updateStudent(id, studentreq);
        if(studentResp == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(studentResp.get());
    }

    //delete student
    @DeleteMapping(/*"{id}"*/ params = "id")
    public ResponseEntity<String> deleteStudent(/*@PathVariable*/ @RequestParam Long id){
        boolean isDeleted = studentService.deleteStudent(id);
        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }
//        return ResponseEntity.ok().body("Student deleted");
        return ResponseEntity.ok("Record Deleted");
    }

    //Softly delete
    @PatchMapping(/*"{id}"*/ params = "id")
    public ResponseEntity<String> deleteStudentSoftly(/*@PathVariable*/ @RequestParam Long id){
        boolean isDeleted = studentService.deleteStudentSoftly(id);

        if(isDeleted){
            return ResponseEntity.ok("Record Deleted Softly");
        }
        return ResponseEntity.notFound().build();
    }
}
