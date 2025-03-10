package com.example.rules;

import com.example.dto.CreateCar;
import com.example.dto.UpdateCar;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;


public class ValidCarValidator implements ConstraintValidator<ValidCar, Object> {

    String yearModel = "yearModel";

    @Override
    public boolean isValid(Object car, ConstraintValidatorContext context) {
        if (car instanceof CreateCar createCar) {
            return validateCreateCar(createCar, context);
        } else if (car instanceof UpdateCar updateCar) {
            return validateUpdateCar(updateCar, context);
        }
        return false;
    }

    private boolean validateCreateCar(CreateCar createCar, ConstraintValidatorContext context) {

        if (Character.isLowerCase(createCar.company().charAt(0))) {
            context.buildConstraintViolationWithTemplate("Company must start with a uppercase letter")
                    .addPropertyNode("company").addConstraintViolation();
            return false;
        }
        if (createCar.yearModel().isAfter(Year.now())) {
            context.buildConstraintViolationWithTemplate("yearModel cannot be after this year")
                    .addPropertyNode(yearModel).addConstraintViolation();
            return false;
        }

        if (createCar.yearModel().isBefore(Year.of(1884))) {
            context.buildConstraintViolationWithTemplate("yearModel cannot be before 1884")
                    .addPropertyNode(yearModel).addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean validateUpdateCar(UpdateCar updateCar, ConstraintValidatorContext context) {
        if (updateCar.company() != null && Character.isLowerCase(updateCar.company().charAt(0))) {
            context.buildConstraintViolationWithTemplate("Company must start with a uppercase letter")
                    .addPropertyNode("company").addConstraintViolation();
            return false;
        }

        if (updateCar.yearModel() != null && updateCar.yearModel().isAfter(Year.now())) {
            context.buildConstraintViolationWithTemplate("yearModel cannot be after this year")
                    .addPropertyNode(yearModel).addConstraintViolation();
            return false;
        }

        if (updateCar.yearModel() != null && updateCar.yearModel().isBefore(Year.of(1884))) {
            context.buildConstraintViolationWithTemplate("yearModel cannot be before 1884")
                    .addPropertyNode(yearModel).addConstraintViolation();
            return false;
        }
        return true;
    }

}
