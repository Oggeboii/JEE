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


@Path("cars")
public class CarResource {

    private CarService carService;

    @Inject
    public CarResource(CarService carService) {
        this.carService = carService;
    }

    public CarResource() {
    }

    //"http://localhost:8080/api/cars/1"
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CarRespons getCar(@PathParam("id") Long id) {
        return carService.getCarById(id);
    }


    //"http://localhost:8080/api/cars"
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CarResponsList getAllCars(){
        return new CarResponsList(carService.getCars());
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewCar(@Valid @NotNull CreateCar car) {
        if(car == null)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Car cannot be null").build();
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
