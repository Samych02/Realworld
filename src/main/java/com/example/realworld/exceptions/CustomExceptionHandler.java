package com.example.realworld.exceptions;

import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
          @NonNull HttpStatusCode status, @NonNull WebRequest request) {
    ErrorBodyGenerator ebg = new ErrorBodyGenerator();
    ex.getBindingResult().getAllErrors().forEach((error) -> ebg.addToMessageList(error.getDefaultMessage()));

    return ResponseEntity.status(status)
            .contentType(MediaType.APPLICATION_JSON)
            .headers(headers)
            .body(ebg.createErrorBody().toString());
  }
}