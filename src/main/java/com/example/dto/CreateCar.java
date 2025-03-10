package com.example.dto;

import com.example.rules.ValidCar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@ValidCar(message = "here is a message")
public record CreateCar(@NotBlank @NotNull String company,
                        @NotBlank @NotNull String model,
                        @NotBlank @NotNull String description,
                        @Positive Long yearModel) {
}
