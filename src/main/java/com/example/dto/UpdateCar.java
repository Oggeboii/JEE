package com.example.dto;

import com.example.rules.ValidCar;

import java.time.Year;


@ValidCar(message = "updateCar message")
public record UpdateCar(String company, String model, String description, Year yearModel) {
}
