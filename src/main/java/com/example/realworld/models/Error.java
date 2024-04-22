package com.example.realworld.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Error {
  private final List<String> body = new ArrayList<>();

  public void addMessage(String message) {
    body.add(message);
  }
}
