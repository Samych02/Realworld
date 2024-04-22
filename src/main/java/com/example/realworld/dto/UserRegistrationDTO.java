package com.example.realworld.dto;

import com.example.realworld.customValidators.FieldType;
import com.example.realworld.customValidators.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDTO {
  @NotBlank(message = "email is required.")
  @Email(message = "Incorrect email format.")
  @Unique(fieldType = FieldType.Email)
  private String email;

  @NotBlank(message = "username is required.")
  @Size(min = 3, max = 20, message = "username length must be between {min} and {max}.")
  @Unique(fieldType = FieldType.Username)
  private String username;

  @NotBlank(message = "password is required.")
  @Size(min = 3, max = 20, message = "password length must be between {min} and {max}.")
  private String password;

  @Size(max = 255)
  @Null
  private String bio;

  @Size(max = 255)
  @Null
  private String image;
}
