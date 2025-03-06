package com.example.persistence;

import jakarta.data.repository.Repository;
import com.example.entity.Car;
import jakarta.data.repository.CrudRepository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
}
