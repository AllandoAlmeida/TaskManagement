package com.dynamic.taskManagement.exceptions;

import java.util.List;
import java.util.ArrayList;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dynamic.taskManagement.exceptions.customeException.NotFoundException;
import com.dynamic.taskManagement.exceptions.dtos.ErrorMessegeDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorMessegeDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErrorMessegeDTO error = new ErrorMessegeDTO(message);
            dto.add(error);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    public GlobalExceptionHandler(MessageSource message) {
        this.messageSource = message;
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(NotFoundException e) {
        ErrorMessegeDTO error = new ErrorMessegeDTO("Task not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}