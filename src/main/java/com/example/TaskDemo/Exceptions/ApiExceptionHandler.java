package com.example.TaskDemo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<?> handleApiRequestException(IdNotFoundException exception){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error message",exception.getMessage());
        errorMap.put("status",HttpStatus.BAD_REQUEST.toString() );
        return  ResponseEntity.ok(errorMap);
    }
}
