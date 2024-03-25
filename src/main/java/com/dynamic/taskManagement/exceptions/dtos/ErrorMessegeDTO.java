package com.dynamic.taskManagement.exceptions.dtos;

import lombok.Data;

@Data
public class ErrorMessegeDTO {
    private String message;

   
    public ErrorMessegeDTO(String message) {
        this.message = message;
    }
}