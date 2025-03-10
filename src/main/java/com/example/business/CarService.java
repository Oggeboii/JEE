package com.example.business;

import com.example.dto.CarRespons;
import com.example.dto.CreateCar;
import com.example.dto.UpdateCar;
import com.example.entity.Car;
import com.example.exceptions.NotFound;
import com.example.mapper.CarMapper;
import com.example.persistence.CarRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


import java.util.List;
import java.util.Objects;

import static com.example.mapper.CarMapper.*;

@ApplicationScoped
public class CarService {


    private CarRepository repository;

    @Inject
    public CarService(CarRepository carRepository) {
        this.repository = carRepository;
    }

    public CarService() {
    }

    public CarRespons getCarById(Long id) {
        return repository.findById(id)
                .map(CarMapper::map)
                .orElseThrow(
                        () -> new NotFound("Car with id " + id + " not found")
                );
    }

    public List<CarRespons> getCars() {
        return repository.findAll()
                .map(CarMapper::map)
                .filter(Objects::nonNull)
                .toList();
    }

    public Car createCar(CreateCar car){
        var newCar = map(car);
        newCar = repository.insert(newCar);
        return newCar;
    }

    public void updateCar(Long id, UpdateCar car){
        var oldCar = repository.findById(id).orElseThrow(() ->
                new NotFound("Car with id " + id + " not found"));
        CarMapper.map(car, oldCar);
        repository.update(oldCar);
    }

    public void deleteCar(Long id){
        var car = repository.findById(id).orElseThrow(()->
                new NotFound("Car with id " + id + " not found"));
        repository.delete(car);
    }


}
