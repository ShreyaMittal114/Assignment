package com.example.assignment.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DeviceNotFound.class,IllegalArgumentException.class, NullPointerException.class, ShelfOperationException.class, ShelfNotFound.class})
    public ResponseEntity<Map<String, String>> handleex(Exception exception){
        Map<String, String> errorResponse = new HashMap<>() ;
        errorResponse.put("message",exception.getMessage());
        errorResponse.put("timestamp", ""+ LocalDateTime.now());
        errorResponse.put("error","bad request");
        errorResponse.put("status",""+ HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }

}
