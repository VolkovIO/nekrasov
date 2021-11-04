package com.example.nekrasov.excepsion;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
