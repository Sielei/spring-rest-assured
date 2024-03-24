package com.spra.springrestassured.common.exception.handler;

import com.spra.springrestassured.job.core.JobNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JobNotFoundException.class)
    ProblemDetail handleJobNotFoundException(JobNotFoundException e){
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Job Not Found");
        problemDetail.setType(URI.create("https://api.spring-rest-assured.com/errors/not-found"));
        problemDetail.setProperty("timestamp", DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                .format(LocalDateTime.now()));
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Validation Error");
        problemDetail.setType(URI.create("https://api.spring-rest-assured.com/errors/validation-error"));
        problemDetail.setProperty("timestamp", DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                .format(LocalDateTime.now()));
        var errors = new HashMap<String, List<String>>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            if (!errors.containsKey(fieldName)) {
                errors.put(fieldName,
                        new ArrayList<>(Collections.singletonList(error.getDefaultMessage())));
            }
            else {
                errors.get(fieldName).add(error.getDefaultMessage());
            }
        });
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }
}
