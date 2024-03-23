package com.spra.springrestassured.job.core;

public class JobNotFoundException extends RuntimeException{

    public JobNotFoundException(String message) {
        super(message);
    }
}
