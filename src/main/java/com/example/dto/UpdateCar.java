package com.example.dto;

import com.example.rules.ValidCar;


@ValidCar(message = "updateCar message")
public record UpdateCar(String company, String model, String description, Long yearModel) {
}
