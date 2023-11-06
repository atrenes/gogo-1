package com.example.gogo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User already has stand") // status code = 409
public class UserAlreadyHasStandException extends RuntimeException {}
