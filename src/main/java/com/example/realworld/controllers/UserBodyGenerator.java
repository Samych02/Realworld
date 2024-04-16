package com.example.realworld.controllers;

import com.auth0.jwt.JWT;
import com.example.realworld.models.User;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class UserBodyGenerator {
  private User user;
  private JWT token;

  public UserBodyGenerator(User user, JWT token) {
    this.user = user;
    this.token = token;
  }

  public JSONObject toBody() {
    JSONObject body = new JSONObject();
    try {
      body.put("email", user.getEmail());
      body.put("token", (token == null) ? JSONObject.NULL : token);
      body.put("username", user.getUsername());
      body.put("bio", (user.getBio() == null) ? JSONObject.NULL : user.getBio());
      body.put("image", (user.getImage() == null) ? JSONObject.NULL : user.getImage());
      return new JSONObject().put("user", body);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }
}
