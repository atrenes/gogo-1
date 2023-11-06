package com.example.gogo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Leaderboard count must be a positive integer")
public class WrongLeaderBoardCountException extends RuntimeException {}
