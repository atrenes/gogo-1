package com.example.gogo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Password must be at least 5 characters long")
public class WeakPasswordException extends RuntimeException {}
