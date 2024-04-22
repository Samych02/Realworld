package com.example.realworld.controllers;

import com.example.realworld.dto.UserAuthenticationDTO;
import com.example.realworld.dto.UserRegistrationDTO;
import com.example.realworld.exceptions.InvalidRequestBodyException;
import com.example.realworld.models.User;
import com.example.realworld.payloads.UserPayload;
import com.example.realworld.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping(path = "/api/users", consumes = "application/json", produces = "application/json")
  @ResponseBody
  public ResponseEntity<?> newUser(@Valid @RequestBody UserPayload<UserRegistrationDTO> userPayloadRequest) {
    // Check if body is empty
    if (userPayloadRequest.getUser() == null) throw new InvalidRequestBodyException();
    // Convert DTO to model
    User newUser = new User(userPayloadRequest.getUser());
    userRepository.save(newUser);
    // Convert model to DTO
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new UserPayload<>(new UserAuthenticationDTO(newUser, null)));
  }
}
