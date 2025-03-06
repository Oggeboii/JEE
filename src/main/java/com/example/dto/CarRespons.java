package com.example.dto;

import com.example.entity.Car;

public record CarRespons(Long id, String company, String model, String description, Long yearModel) {


        public CarRespons(Car car) {
                this(car.getId(), car.getCompany(), car.getModel(), car.getDescription(), car.getYearModel());
        }
        public static CarRespons map(Car car) {
                return new CarRespons(car.getId(), car.getCompany(), car.getModel(), car.getDescription(), car.getYearModel());
        }
}
