package com.example.rules;

import com.example.dto.UpdateCar;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class ValidUpdateCarValidator implements ConstraintValidator<ValidCar, UpdateCar> {
    String yearModel = "yearModel";

    public boolean isValid(UpdateCar updateCar, ConstraintValidatorContext context) {

        if (Character.isLowerCase(updateCar.company().charAt(0))) {
            context.buildConstraintViolationWithTemplate("Company must start with a uppercase letter")
                    .addPropertyNode("company").addConstraintViolation();
            return false;
        }
        if (Year.of(updateCar.yearModel()).isAfter(Year.now())) {
            context.buildConstraintViolationWithTemplate("yearModel cannot be after this year")
                    .addPropertyNode(yearModel).addConstraintViolation();
            return false;
        }

        if (Year.of(updateCar.yearModel()).isBefore(Year.of(1884))) {
            context.buildConstraintViolationWithTemplate("yearModel cannot be before 1884")
                    .addPropertyNode(yearModel).addConstraintViolation();
            return false;
        }
        return true;
    }
}
