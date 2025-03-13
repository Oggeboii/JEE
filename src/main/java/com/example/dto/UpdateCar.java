package com.example.dto;

import com.example.rules.ValidCar;

import java.time.Year;


@ValidCar(message = "Unable to update car")
public record UpdateCar(String company, String model, String description, Year yearModel) {
}
