package com.example.rules;

import com.example.dto.CreateCar;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidCarValidator implements ConstraintValidator<ValidCar, CreateCar> {
    @Override
    public boolean isValid(CreateCar createCar, ConstraintValidatorContext context) {
        if(Character.isLowerCase(createCar.company().charAt(0))) {
            context.buildConstraintViolationWithTemplate("Company must start with a uppercase letter")
                    .addPropertyNode("company").addConstraintViolation();
            return false;
        }
        if (createCar.yearModel() > LocalDate.now().getYear()){
            context.buildConstraintViolationWithTemplate("yearModel cannot be greater than current year")
                    .addPropertyNode("company").addConstraintViolation();
            return false;
        }

        if(createCar.yearModel() < 1884)
            return false;

        return true;
    }
}
