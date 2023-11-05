package com.example.gogo.exception;

public class UserAlreadyHasStandException extends RuntimeException {
    public UserAlreadyHasStandException(String message) {
        super(message);
    }
}
