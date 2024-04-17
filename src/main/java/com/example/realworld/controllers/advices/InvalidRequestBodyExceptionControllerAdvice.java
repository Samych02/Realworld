package com.example.realworld.controllers.advices;

import com.example.realworld.controllers.utils.ErrorJsonBodyBuilder;
import com.example.realworld.exceptions.InvalidRequestBodyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidRequestBodyExceptionControllerAdvice {
  @ExceptionHandler(InvalidRequestBodyException.class)
  public ResponseEntity<?> exceptionInvalidRequestBodyHandler() {
    ErrorJsonBodyBuilder errorJsonBodyBuilder = new ErrorJsonBodyBuilder();
    errorJsonBodyBuilder.addToMessageList("Invalid request body");
    return ResponseEntity
            .badRequest()
            .body(errorJsonBodyBuilder.createErrorBody());
  }

}
