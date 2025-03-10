package com.example.rules;

import com.example.dto.CreateCar;
import com.example.dto.UpdateCar;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidCarValidator implements ConstraintValidator<ValidCar, Object> {
    @Override
    public boolean isValid(Object car, ConstraintValidatorContext context) {
        if (car instanceof CreateCar) {
            CreateCar createCar = (CreateCar) car;
            return validateCreateCar(createCar, context);
        } else if (car instanceof UpdateCar) {
            UpdateCar updateCar = (UpdateCar) car;
            return validateUpdateCar(updateCar, context);
        }
        return false;
    }
    private boolean validateCreateCar(CreateCar createCar, ConstraintValidatorContext context) {

        if(Character.isLowerCase(createCar.company().charAt(0))) {
            context.buildConstraintViolationWithTemplate("Company must start with a uppercase letter")
                    .addPropertyNode("company").addConstraintViolation();
            return false;
        }
        if (createCar.yearModel() > LocalDate.now().getYear()){
            context.buildConstraintViolationWithTemplate("yearModel cannot be greater than current year")
                    .addPropertyNode("yearModel").addConstraintViolation();
            return false;
        }

        if(createCar.yearModel() < 1884)
            return false;

        return true;
    }
    private boolean validateUpdateCar(UpdateCar updateCar, ConstraintValidatorContext context) {
        if(Character.isLowerCase(updateCar.company().charAt(0))) {
            context.buildConstraintViolationWithTemplate("Company must start with a uppercase letter")
                    .addPropertyNode("company").addConstraintViolation();
            return false;
        }
        if (updateCar.yearModel() > LocalDate.now().getYear()){
            context.buildConstraintViolationWithTemplate("yearModel cannot be greater than current year")
                    .addPropertyNode("yearModel").addConstraintViolation();
            return false;
        }

        if(updateCar.yearModel() < 1884)
            return false;

        return true;
    }
}
