package com.example.realworld.controllers.advices;

import com.example.realworld.models.Error;
import com.example.realworld.payloads.ErrorPayload;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    Error error = new Error();
    ex.getBindingResult().getAllErrors().forEach((errorMessage) -> error.addMessage(errorMessage.getDefaultMessage()));
    return ResponseEntity.status(status)
            .headers(headers)
            .body(new ErrorPayload(error));
  }
}