package com.example.gogo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Stand ID doesn't exist")
public class StandNotFoundByIdException extends RuntimeException {
    public StandNotFoundByIdException(String message) {
        super(message);
    }
}
