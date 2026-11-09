package com.example.demoStudentManagementDto.DataTransferObjects_DTOs;

import java.time.LocalDateTime;

public class UpdateStudentResponseDto {
    private UpdateStudentInfoDto updateStudentInfoDto;
    private String message;

    public UpdateStudentInfoDto getUpdateStudentInfoDto() {
        return updateStudentInfoDto;
    }

    public void setUpdateStudentInfoDto(UpdateStudentInfoDto updateStudentInfoDto) {
        this.updateStudentInfoDto = updateStudentInfoDto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
