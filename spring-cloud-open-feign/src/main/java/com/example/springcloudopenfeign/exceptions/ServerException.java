package com.example.springcloudopenfeign.exceptions;

public class ServerException extends RuntimeException {
    public ServerException(String message) {
        super(message);
    }
}
