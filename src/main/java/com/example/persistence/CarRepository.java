package com.example.persistence;


import jakarta.data.repository.*;
import com.example.entity.Car;


import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    @Find
    Optional <Car> findByLicenseNumber(String licenseNumber);

    @Find
    List<Car> findByCompany(String company);

    @Query("select c from Car c where c.yearModel >= :yearModelStart and c.yearModel <= :yearModelEnd")
    List<Car> findByYearModelBetween(@Param("yearModelStart") Year yearModelStart, @Param("yearModelEnd") Year yearModelEnd);

    @Find
    List<Car> findByYearModel(Year yearModel);

    @Find
    List<Car> findByModel(String model);
}
