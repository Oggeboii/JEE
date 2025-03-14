package com.example.presentation;

import com.example.business.CarService;
import com.example.dto.CarResponsList;
import com.example.dto.UpdateCar;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.example.dto.CreateCar;
import com.example.dto.CarRespons;
import com.example.entity.Car;

import java.time.Year;


@Path("cars")
public class CarResource {

    private CarService carService;

    @Inject
    public CarResource(CarService carService) {
        this.carService = carService;
    }

    public CarResource() {
    }

    //"http://localhost:8080/api/cars/one"
    @GET
    @Path("one")
    @Produces(MediaType.APPLICATION_JSON)
    public CarRespons getCar(@QueryParam("id") Long id, @QueryParam("licenseNumber") String licenseNumber) {
        if (id != null) {
            return carService.getCarById(id);
            //"http://localhost:8080/api/cars/one?id=1"
        } else
            return carService.getCarByLicenseNumber(licenseNumber);
        //"http://localhost:8080/api/cars/one?licenseNumber=ABC123"
    }

    //"http://localhost:8080/api/cars"
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CarResponsList getCars(@QueryParam("company") String company, @QueryParam("yearModelStart") Integer yearModelStart, @QueryParam("yearModelEnd") Integer yearModelEnd, @QueryParam("model") String model) {
        if (company != null && !company.isEmpty()) {
            return new CarResponsList(carService.getCarsByCompany(company));
            //"http://localhost:8080/api/cars?company=Volvo"
        } else if (yearModelStart != null && yearModelEnd != null) {
            return new CarResponsList(carService.getCarsBetweenYears(Year.of(yearModelStart), Year.of(yearModelEnd)));
            //"http://localhost:8080/api/cars?yearModelStart=2000&yearModelEnd=2003"
        } else if (model != null && !model.isEmpty()) {
            return new CarResponsList(carService.getCarsByModel(model));
            //"http://localhost:8080/api/cars?model=V70"
        } else {
            return new CarResponsList(carService.getCars());
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewCar(@Valid @NotNull CreateCar car) {
        if (carService.licenseNumberExists(car.licenseNumber())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("License number already exists")
                    .build();
        }
        if (carService.combinationExists(car.company(), car.model(), Year.of(car.yearModel())))
            return Response.status(Response.Status.CONFLICT)
                    .entity("Combination already exists")
                    .build();

        Car newCar = carService.createCar(car);
        return Response.status(Response.Status.CREATED)
                .header("Location", "/api/cars/" + newCar.getId())
                .build();
    }

    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCar(@PathParam("id") Long id, @Valid @NotNull UpdateCar car) {
        carService.updateCar(id, car);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCar(@PathParam("id") Long id) {
        carService.deleteCar(id);
        return Response.noContent().build();
    }


}
