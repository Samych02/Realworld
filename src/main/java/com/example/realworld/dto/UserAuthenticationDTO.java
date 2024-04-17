package com.example.realworld.dto;

import com.auth0.jwt.JWT;
import com.example.realworld.models.User;
import lombok.Data;

@Data
public class UserAuthenticationDTO {
  private String email;

  private String username;

  private JWT token;

  private String bio;

  private String image;

  public UserAuthenticationDTO(User user, JWT token) {
    this.email = user.getEmail();
    this.username = user.getUsername();
    this.token = token;
    this.bio = user.getBio();
    this.image = user.getImage();
  }
}
