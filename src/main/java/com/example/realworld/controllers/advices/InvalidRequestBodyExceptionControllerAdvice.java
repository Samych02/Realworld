package com.example.realworld.controllers.advices;

import com.example.realworld.exceptions.InvalidRequestBodyException;
import com.example.realworld.models.Error;
import com.example.realworld.payloads.ErrorPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidRequestBodyExceptionControllerAdvice {
  @ExceptionHandler({InvalidRequestBodyException.class})
  public ResponseEntity<?> exceptionInvalidRequestBodyHandler() {
    Error error = new Error();
    error.addMessage("Invalid request body");
    return ResponseEntity
            .badRequest()
            .body(new ErrorPayload(error));
  }
}
