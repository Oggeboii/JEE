package com.example.dto;

import com.example.rules.ValidCar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;


@ValidCar(message = "Unable to create car")
public record CreateCar(@NotBlank @NotNull String company,
                        @NotBlank @NotNull String model,
                        @NotBlank @NotNull String description,
                        @Positive Integer yearModel,
                        @Pattern(regexp = "^[A-Za-z]{3}\\d{3}$") String licenseNumber) {
}
