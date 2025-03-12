package com.example.rules;

import com.example.dto.CreateCar;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;


public class ValidCreateCarValidator implements ConstraintValidator<ValidCar, CreateCar> {

    String yearModel = "yearModel";

    @Override
    public boolean isValid(CreateCar createCar, ConstraintValidatorContext context) {

        if (Character.isLowerCase(createCar.company().charAt(0))) {
            context.buildConstraintViolationWithTemplate("Company must start with a uppercase letter")
                    .addPropertyNode("company").addConstraintViolation();
            return false;
        }
        if (Year.of(createCar.yearModel()).isAfter(Year.now())) {
            context.buildConstraintViolationWithTemplate("yearModel cannot be after this year")
                    .addPropertyNode(yearModel).addConstraintViolation();
            return false;
        }

        if (Year.of(createCar.yearModel()).isBefore(Year.of(1884))) {
            context.buildConstraintViolationWithTemplate("yearModel cannot be before 1884")
                    .addPropertyNode(yearModel).addConstraintViolation();
            return false;
        }

        return true;
    }
}
