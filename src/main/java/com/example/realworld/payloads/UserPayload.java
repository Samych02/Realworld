package com.example.realworld.payloads;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Nest an object in user field
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayload<T> {
  @Valid
  private T user;
}
