package com.example.business;

import com.example.dto.CarRespons;
import com.example.dto.CreateCar;
import com.example.dto.UpdateCar;
import com.example.entity.Car;
import com.example.exceptions.NotFound;
import com.example.mapper.CarMapper;
import com.example.persistence.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    static List<Car> cars() {
        List<Car> cars = new ArrayList<>();

        Car car = new Car();
        car.setId(1L);
        car.setCompany("Volvo");
        car.setModel("V70");
        car.setDescription("Blue");
        car.setYearModel(Year.of(2000));
        car.setLicenseNumber("ABC123");

        Car car2 = new Car();
        car2.setId(2L);
        car2.setCompany("Volvo");
        car2.setModel("V70");
        car2.setDescription("Black");
        car2.setYearModel(Year.of(2000));
        car2.setLicenseNumber("DEF456");

        cars.add(car);
        cars.add(car2);
        return cars;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Car with correct Id is returned when getCarById is called")
    void carWithCorrectIdIsReturnedWhenGetCarByIdIsCalled() {
        Car car = new Car();
        car.setId(1L);
        car.setCompany("Volvo");
        car.setModel("V70");
        car.setDescription("Blue");
        car.setYearModel(Year.of(2000));
        car.setLicenseNumber("ABC123");
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        CarRespons carRespons = carService.getCarById(1L);
        assertNotNull(carRespons);
        assertEquals(1L, carRespons.id());
    }

    @Test
    @DisplayName("Throws NotFound exception when car with given Id is not found")
    void throwsNotFoundExceptionWhenCarWithGivenIdIsNotFound() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFound.class, () -> carService.getCarById(1L));
    }

    @Test
    @DisplayName("Car with correct licens number is returned when getCarByLicensNumber is called")
    void carWithCorrectLicensNumberIsReturnedWhenGetCarByLicensNumberIsCalled() {
        Car car = new Car();
        car.setId(1L);
        car.setCompany("Volvo");
        car.setModel("V70");
        car.setDescription("Blue");
        car.setYearModel(Year.of(2000));
        car.setLicenseNumber("ABC123");
        when(carRepository.findByLicenseNumber("ABC123")).thenReturn(Optional.of(car));
        CarRespons carRespons = carService.getCarByLicenseNumber("ABC123");

        assertEquals("ABC123", carRespons.licenseNumber());
    }

    @Test
    @DisplayName("Throws NotFound exception when car with given license number is not found")
    void throwsNotFoundExceptionWhenCarWithGivenLicenseNumberIsNotFound() {
        when(carRepository.findByLicenseNumber("ABC123")).thenReturn(Optional.empty());

        assertThrows(NotFound.class, () -> carService.getCarByLicenseNumber("ABC123"));
    }

    @Test
    @DisplayName("Returns all cars from same company when GetCarsByCompany is called")
    void returnsAllCarsFromSameCompanyWhenGetCarsByCompanyIsCalled() {
        List<Car> cars = cars();

        when(carRepository.findByCompany("Volvo")).thenReturn(cars);

        List<CarRespons> carRespons = carService.getCarsByCompany("Volvo");
        assertEquals(2, carRespons.size());
    }

    @Test
    @DisplayName("Returns all cars within year intervall when GetCarsByYear is called")
    void returnsAllCarsWithinYearIntervallWhenGetCarsByYearIsCalled() {
        List<Car> cars = cars();

        when(carRepository.findByYearModelBetween(Year.of(2000), Year.of(2001))).thenReturn(cars);

        List<CarRespons> carRespons = carService.getCarsBetweenYears(Year.of(2000), Year.of(2001));
        assertEquals(2, carRespons.size());
    }

    @Test
    @DisplayName("Returns all cars of the same model when GetCarsByModel is called")
    void returnsAllCarsOfTheSameModelWhenGetCarsByModelIsCalled() {
        List<Car> cars = cars();

        when(carRepository.findByModel("V70")).thenReturn(cars);

        List<CarRespons> carRespons = carService.getCarsByModel("V70");
        assertEquals(2, carRespons.size());
    }

    @Test
    @DisplayName("CombinationExists returns true when the combination already exists")
    void combinationExistsReturnsTrueWhenTheCombinationAlreadyExists() {
        Car car = new Car();
        car.setModel("ModelX");
        car.setYearModel(Year.of(2020));
        when(carRepository.findByCompany("Tesla")).thenReturn(List.of(car));

        assertTrue(carService.combinationExists("Tesla", "ModelX", Year.of(2020)));
    }
    
    @Test
    @DisplayName("licensNumberExist return true")
    void licensNumberExistReturnTrue(){
        Car car = new Car();
        car.setId(1L);
        car.setCompany("Volvo");
        car.setModel("V70");
        car.setDescription("Blue");
        car.setYearModel(Year.of(2000));
        car.setLicenseNumber("ABC123");

        when(carRepository.findByLicenseNumber("ABC123")).thenReturn(Optional.of(car));
        assertTrue(carService.licenseNumberExists(car.getLicenseNumber()));
        verify(carRepository, times(1)).findByLicenseNumber("ABC123");
    }

    @Test
    @DisplayName("licensNumberExist return false")
    void licensNumberExistReturnFalse(){
        Car car = new Car();
        car.setId(1L);
        car.setCompany("Volvo");
        car.setModel("V70");
        car.setDescription("Blue");
        car.setYearModel(Year.of(2000));
        car.setLicenseNumber("ABC123");

        when(carRepository.findByLicenseNumber("XYZ456")).thenReturn(Optional.of(car));
        assertFalse(carService.licenseNumberExists(car.getLicenseNumber()));
        verify(carRepository, times(1)).findByLicenseNumber("ABC123");
    }

    @Test
    @DisplayName("All cars are returned when getCars is called")
    void allCarsAreReturnedWhenGetCarsIsCalled() {
        List<Car> cars = cars();
        when(carRepository.findAll()).thenReturn(cars.stream());
        List<CarRespons> carRespons = carService.getCars();
        assertEquals(2, carRespons.size());
    }

    @Test
    @DisplayName("CreateCar should return CreatedCar")
    void createCarShouldReturnCreatedCar() {
        CreateCar createCar = new CreateCar("Volvo", "V70", "Blue", 2001, "DEF456");

        Car mappedCar = CarMapper.map(createCar);
        mappedCar.setId(1L); // Simulera att ID:t sätts efter insättning i databasen

        when(carRepository.insert(any(Car.class))).thenReturn(mappedCar);

        Car result = carService.createCar(createCar);

        assertNotNull(result);
        assertEquals(mappedCar, result);
        verify(carRepository, times(1)).insert(any(Car.class));
    }

    @Test
    @DisplayName("UpdateCar should updated car")
    void updateCarShouldUpdatedCar() {
        Long carId = 1L;
        UpdateCar updateCar = new UpdateCar("Volvo", "V70", "Red", 2002);

        Car oldCar = new Car();
        oldCar.setId(carId);
        oldCar.setCompany("Volvo");
        oldCar.setModel("V70");
        oldCar.setDescription("Blue");
        oldCar.setYearModel(Year.of(2001));

        when(carRepository.findById(carId)).thenReturn(Optional.of(oldCar));

        carService.updateCar(carId, updateCar);

        verify(carRepository, times(1)).findById(carId);
        verify(carRepository, times(1)).update(oldCar);

        assertEquals("Red", oldCar.getDescription());
        assertEquals(Year.of(2002), oldCar.getYearModel());
    }

    @Test
    @DisplayName("NotFound is thrown if updateCar can not find car with id")
    void notFoundIsThrownIfUpdateCarCanNotFindCarWithId() {
        Long carId = 1L;
        UpdateCar updateCar = new UpdateCar("Volvo", "V70", "Red", 2002);

        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFound.class, () -> carService.updateCar(carId, updateCar));

        String expectedMessage = "Car with id " + carId + " not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(carRepository, times(1)).findById(carId);
        verify(carRepository, times(0)).update(any(Car.class));
    }

    @Test
    @DisplayName("Car is deleted when deleteCar is called")
    void carIsDeletedWhenDeleteCarIsCalled() {
        Long carId = 1L;
        Car car = new Car();
        car.setId(carId);
        car.setCompany("Volvo");
        car.setModel("V70");
        car.setDescription("Blue");
        car.setYearModel(Year.of(2001));
        car.setLicenseNumber("DEF456");

        when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        carService.deleteCar(carId);

        verify(carRepository, times(1)).findById(carId);
        verify(carRepository, times(1)).delete(car);
    }

    @Test
    @DisplayName("NotFound is thrown if deleteCar can not find car with id")
    void notFoundIsThrownIfDeleteCarCanNotFindCarWithId() {
        Long carId = 1L;

        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFound.class, () -> carService.deleteCar(carId));

        String expectedMessage = "Car with id " + carId + " not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(carRepository, times(1)).findById(carId);
        verify(carRepository, times(0)).delete(any(Car.class));
    }
}
