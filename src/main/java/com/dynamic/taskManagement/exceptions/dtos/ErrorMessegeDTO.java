package com.dynamic.taskManagement.exceptions.dtos;

import lombok.Data;

@Data
public class ErrorMessegeDTO {
    private String message;
    private String field;

   
    public ErrorMessegeDTO(String message, String field) {
        this.message = message;
        this.field = field;
    }
}