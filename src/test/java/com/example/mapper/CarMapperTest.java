package com.example.mapper;

import com.example.dto.CarRespons;
import com.example.dto.CreateCar;
import com.example.entity.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class CarMapperTest {

    @Test
    @DisplayName("Map Car to CarRespons should be same")
    void mapCarToCarResponsShouldBeSame() {
        Car car = new Car();
        car.setId(1L);
        car.setCompany("Volvo");
        car.setModel("V70");
        car.setDescription("Blue");
        car.setYearModel(Year.of(2001));
        car.setLicenseNumber("ABC123");

        CarRespons carRespons = CarMapper.map(car);
        assertNotNull(carRespons);
        assertEquals(1L, carRespons.id());
        assertEquals("Volvo", carRespons.company());
        assertEquals("V70", carRespons.model());
        assertEquals("Blue", carRespons.description());
        assertEquals(2001, carRespons.yearModel());
        assertEquals("ABC123", carRespons.licenseNumber());
    }

    @Test
    @DisplayName("Map car to CarRespons returns null")
    void mapCarToCarResponsReturnsNull() {
        Car car = null;
        CarRespons carRespons = CarMapper.map(car);
        assertNull(carRespons);
    }


    @Test
    @DisplayName("Map createCar t Car")
    void mapCreateCarTpCar() {
        CreateCar createCar = new CreateCar(
                "Volvo", "V70", "Blue", 2001, "ABC123");
        Car car = CarMapper.map(createCar);
        assertNotNull(car);
        assertEquals("Volvo", car.getCompany());
        assertEquals("V70", car.getModel());
        assertEquals("Blue", car.getDescription());
        assertEquals(Year.of(2001), car.getYearModel());
        assertEquals("ABC123", car.getLicenseNumber());
    }


}
