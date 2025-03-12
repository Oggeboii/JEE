package com.example.persistence;


import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import com.example.entity.Car;
import jakarta.data.repository.CrudRepository;

import java.awt.*;
import java.time.Year;
import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    @Find
    List<Car> findByCompany(String company);

    @Find
    List<Car> findByYearModel(Year yearModel);

    @Find
    List<Car> findByModel(String model);
}
