package com.example.realworld.customValidator;

import com.example.realworld.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueValidator implements ConstraintValidator<Unique, String> {
  @Autowired private UserRepository userRepository;
  private FieldType fieldType;

  @Override
  public void initialize(Unique constraintAnnotation) {
    fieldType = constraintAnnotation.fieldType();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    switch (fieldType) {
      case Email -> {
        return !userRepository.existsByEmail(s);
      }
      case Username -> {
        return !userRepository.existsByUsername(s);
      }
      default -> {
        return false;
      }
    }
  }
}
