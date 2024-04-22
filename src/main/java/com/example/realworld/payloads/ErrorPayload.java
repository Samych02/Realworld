package com.example.realworld.payloads;

import com.example.realworld.models.Error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Nest an object in user field
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorPayload {
  private Error errors;
}
