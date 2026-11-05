package com.example.demoStudentManagementDto.Services;

import com.example.demoStudentManagementDto.DataTransferObjects_DTOs.CreateStudentRequestDto;
import com.example.demoStudentManagementDto.DataTransferObjects_DTOs.CreateStudentResponseDto;
import com.example.demoStudentManagementDto.Entities.Student;
import com.example.demoStudentManagementDto.Repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    private Student mapToStudentEntity(CreateStudentRequestDto studentRequestDto){
        Student student = new Student();

        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setEmail(studentRequestDto.getEmail());
        student.setRollNo(studentRequestDto.getRollNo());
        student.setSubject(studentRequestDto.getSubject());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        student.setDeleted(false);

        return student;
    }

    private CreateStudentResponseDto mapToRespDto(Student student){
        CreateStudentResponseDto responseDto = new CreateStudentResponseDto();

        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setId(student.getId());
        responseDto.setEmail(student.getEmail());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setMessage("Student saved successfully");
        responseDto.setCreatedAt(student.getCreatedAt());
        responseDto.setUpdatedAt(student.getUpdatedAt());

        return responseDto;
    }

    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentRequestDto){
        Student student = mapToStudentEntity(studentRequestDto);

        Student studentResp = studentRepository.save(student);

        return mapToRespDto(studentResp);
    }
}
