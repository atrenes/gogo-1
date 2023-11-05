package com.example.gogo.exception;

public class UserNotFoundByNameException extends RuntimeException {
    public UserNotFoundByNameException(String message) {
        super(message);
    }
}
