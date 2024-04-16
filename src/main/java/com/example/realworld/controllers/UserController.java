package com.example.realworld.controllers;

import com.example.realworld.models.User;
import com.example.realworld.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping(path = "/api/users", consumes = "application/json")
  public ResponseEntity<?> newUser(@RequestBody Map<String, Object> lookupRequestObject) {
    System.out.println(lookupRequestObject.toString());
    @Valid User newUser = new ObjectMapper().convertValue(lookupRequestObject.get("user"), User.class);
    newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
    userRepository.addUser(newUser);
    return ResponseEntity.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new UserBodyGenerator(newUser, null).toBody().toString());
  }
}
