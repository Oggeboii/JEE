package com.example.dto;

import com.example.rules.ValidCar;




@ValidCar(message = "Unable to update car")
public record UpdateCar(String company, String model, String description, Integer yearModel) {
}
