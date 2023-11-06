package com.example.gogo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User ID does not exist")
public class UserNotFoundByIdException extends RuntimeException {}
