package com.example.persistence;


import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import com.example.entity.Car;
import jakarta.data.repository.CrudRepository;


import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    @Find
    Optional <Car> findByLicenseNumber(String licenseNumber);

    @Find
    List<Car> findByCompany(String company);

    @Find
    List<Car> findByYearModel(Year yearModel);

    @Find
    List<Car> findByModel(String model);
}
