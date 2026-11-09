package com.example.demoStudentManagementDto.Services;

import com.example.demoStudentManagementDto.DataTransferObjects_DTOs.*;
import com.example.demoStudentManagementDto.Entities.Student;
import com.example.demoStudentManagementDto.Exceptions.DuplicateResourceException;
import com.example.demoStudentManagementDto.Exceptions.ResourceNotFoundException;
import com.example.demoStudentManagementDto.Repositories.StudentRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

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
        StudentInfoDto studentInfoDto = new StudentInfoDto();

        studentInfoDto.setName(student.getName());
        studentInfoDto.setAge(student.getAge());
        studentInfoDto.setId(student.getId());
        studentInfoDto.setEmail(student.getEmail());
        studentInfoDto.setRollNo(student.getRollNo());
        studentInfoDto.setSubject(student.getSubject());
        responseDto.setMessage("Student saved successfully");
        studentInfoDto.setCreatedAt(student.getCreatedAt());
        studentInfoDto.setUpdatedAt(student.getUpdatedAt());
        responseDto.setStudentInfoDto(studentInfoDto);

        return responseDto;
    }

    private CreateStudentResponseDTOforAll mapToRespDtoAll(Student student){
        CreateStudentResponseDTOforAll responseDTO = new CreateStudentResponseDTOforAll();

        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setCreatedAt(student.getCreatedAt());
        responseDTO.setUpdatedAt(student.getUpdatedAt());

        return responseDTO;

    }

    private UpdateStudentResponseDto mapToUpdateDto(Student student){
        UpdateStudentResponseDto respDto = new UpdateStudentResponseDto();
        UpdateStudentInfoDto studentInfo = new UpdateStudentInfoDto();
        studentInfo.setId(student.getId());
        studentInfo.setName(student.getName());
        studentInfo.setAge(student.getAge());
        studentInfo.setEmail(student.getEmail());
        studentInfo.setRollNo(student.getRollNo());
        studentInfo.setSubject(student.getSubject());
        respDto.setMessage("Student Updated Successfully");
        studentInfo.setUpdatedAt(student.getUpdatedAt());

        respDto.setUpdateStudentInfoDto(studentInfo);
        return respDto;
    }

    private ApiRespDtoForDelete mapToDelete(){
        ApiRespDtoForDelete resp = new ApiRespDtoForDelete();
        resp.setMessage("Student deleted successfully");
        return resp;
    }

    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentRequestDto){
        boolean emailExists = studentRepository.existsByEmailAndDeletedIsFalse(studentRequestDto.getEmail());
        if(emailExists){
            throw new DuplicateResourceException(
                    "Student with email " + studentRequestDto.getEmail() + " already exists"
            );
        }
        Student student = mapToStudentEntity(studentRequestDto);

        Student studentResp = studentRepository.save(student);

        return mapToRespDto(studentResp);
    }

    public List<CreateStudentResponseDTOforAll> getAllStudent(){
        List<Student> studentList =
                studentRepository.findByDeletedIsFalse();

        if(studentList.isEmpty()){
            throw new ResourceNotFoundException(
                    "List is Empty"
            );
        }

        return studentList //Gets the list of students from the database
                .stream()//Processes the students one by one
                .map(this::mapToRespDtoAll)//Converts each Student object into a CreateStudentResponseDto objcect
                .toList();//Collects all converted DTO objects into a new List
    }

    public CreateStudentResponseDto getStudent(Long id){
//        Student studentResp = studentRepository
//                .findByIdAndDeletedIsFalse(id)
//                .orElseThrow(() ->
//                        new RuntimeException("Student not found"));

        Student studentResp = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found id with: " + id
                        )
                        );

        return mapToRespDto(studentResp);
    }

    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentReq){
        Student existingStudent = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                        "Student not found with id: " + id
                                )
                );

        existingStudent.setName(studentReq.getName());
        existingStudent.setRollNo(studentReq.getRollNo());
        existingStudent.setSubject(studentReq.getSubject());
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setDeleted(false);
        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(existingStudent);

        return mapToUpdateDto(savedStudent);
    }

    public void deleteStudent(Long id){
        Student student = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Student not found with id: " + id
                        )
                );
        student.setDeleted(true);
        studentRepository.save(student);
//        return mapToDelete();
    }
}