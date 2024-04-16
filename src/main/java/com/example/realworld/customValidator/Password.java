package com.example.realworld.customValidator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
  String message() default "Password length must be between {min} and {max}";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
  int min() default 0;
  int max() default 255;
}
