package com.example.realworld.controllers.utils;

import jakarta.validation.Valid;
import lombok.Data;

// Nest an object in user field
@Data
public class UserPayload<T> {
  @Valid
  private T user;
}
