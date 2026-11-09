package com.example.demoStudentManagementDto.DataTransferObjects_DTOs;

import java.time.LocalDateTime;

public class CreateStudentResponseDto {
    private StudentInfoDto studentInfoDto;
    private String message;

    public StudentInfoDto getStudentInfoDto() {
        return studentInfoDto;
    }

    public void setStudentInfoDto(StudentInfoDto studentInfoDto) {
        this.studentInfoDto = studentInfoDto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
