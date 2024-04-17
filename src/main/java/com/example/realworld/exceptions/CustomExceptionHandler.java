package com.example.realworld.exceptions;

import com.example.realworld.controllers.utils.ErrorJsonBodyBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  /*
  This method creates an array of all error messages raised when validating a class,
  then send back an HTTP response with the correct status (4xx) with a body that looks
  like the following json:
  {
      "errors": {
          "body": [
              "password is required.",
              "email is required.",
              "username is required."
          ]
      }
  }
  * */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
          @NonNull HttpStatusCode status, @NonNull WebRequest request) {
    ErrorJsonBodyBuilder ebg = new ErrorJsonBodyBuilder();
    ex.getBindingResult().getAllErrors().forEach((error) -> ebg.addToMessageList(error.getDefaultMessage()));

    return ResponseEntity.status(status)
            .contentType(MediaType.APPLICATION_JSON)
            .headers(headers)
            .body(ebg.createErrorBody());
  }
}