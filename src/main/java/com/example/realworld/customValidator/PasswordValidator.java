package com.example.realworld.customValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {
  private int min;
  private int max;

  @Override
  public void initialize(Password constraintAnnotation) {
    min = constraintAnnotation.min();
    max = constraintAnnotation.max();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (s == null) {
      return false;
    }

    if (s.startsWith("$2a$10$") && s.length() == 60) {
      return true;
    }
    return ((s.length() >= min && s.length() <= max));
  }
}
