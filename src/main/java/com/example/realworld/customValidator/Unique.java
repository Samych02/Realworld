package com.example.realworld.customValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Check if a value is unique in a table
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface Unique {
  String message() default "{fieldType} is already in use.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  FieldType fieldType();
}
