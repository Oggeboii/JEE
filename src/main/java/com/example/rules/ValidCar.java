package com.example.rules;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = {ValidCreateCarValidator.class, ValidUpdateCarValidator.class})
    public @interface ValidCar {
       String message() default "Not a valid car";

       Class<?>[] groups() default {};

       Class<? extends Payload>[] payload() default {};
    }
