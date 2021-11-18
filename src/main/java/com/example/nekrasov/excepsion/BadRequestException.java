package com.example.nekrasov.excepsion;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
