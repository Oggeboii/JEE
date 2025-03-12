package com.example.dto;


import java.time.Year;

public record CarRespons(Long id, String company, String model, String description, Integer yearModel, String licenseNumber) {
}
