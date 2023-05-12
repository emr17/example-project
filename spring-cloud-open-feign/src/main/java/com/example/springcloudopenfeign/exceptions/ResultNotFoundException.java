package com.example.springcloudopenfeign.exceptions;
public class ResultNotFoundException extends RuntimeException{
    public ResultNotFoundException(String message) {
        super(message);
    }
}
