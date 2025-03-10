package com.example.mapper;

import com.example.dto.CarRespons;
import com.example.dto.CreateCar;
import com.example.dto.UpdateCar;
import com.example.entity.Car;

public class CarMapper {
    private CarMapper() {}

    public static CarRespons map(Car car) {
        if(null == car)
            return null;
        return new CarRespons(car.getId(), car.getCompany(), car.getModel(), car.getDescription(), car.getYearModel().getValue());
    }

    public static Car map(CreateCar car) {
        if(null == car)
            return null;
        Car newCar = new Car();
        newCar.setCompany(car.company());
        newCar.setModel(car.model());
        newCar.setDescription(car.description());
        newCar.setYearModel(car.yearModel());
        return newCar;
    }

    public static void map(UpdateCar updateCar, Car existingCar) {
        if (updateCar.company() != null) {
            existingCar.setCompany(updateCar.company());
        }
        if (updateCar.description() != null) {
            existingCar.setDescription(updateCar.description());
        }
        if (updateCar.model() != null) {
            existingCar.setModel(updateCar.model());
        }
        if (updateCar.yearModel() != null) {
            existingCar.setYearModel(updateCar.yearModel());
        }
    }




}
