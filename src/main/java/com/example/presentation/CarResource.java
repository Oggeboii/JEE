package com.example.presentation;

import com.example.persistence.CarRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.example.dto.CreateCar;
import com.example.dto.CarRespons;
import com.example.entity.Car;
import com.example.mapper.CarMapper;

import java.util.List;
import java.util.Objects;

@Path("cars")
public class CarResource {

    private CarRepository repository;

    @Inject
    public CarResource(CarRepository repository) {
        this.repository = repository;
    }

    public CarResource() {
    }


    //"http://localhost:8080/api/cars"
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarRespons> getCars() {
        return repository.findAll()
                .map(CarRespons::new)
                .filter(Objects::nonNull)
                .toList();
    }




    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewCar(CreateCar car) {
        if(car == null)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Car cannot be null").build();
        Car newCar = CarMapper.map(car);
        newCar = repository.insert(newCar);
        return Response.status(Response.Status.CREATED)
                .header("Location", "/api/cars/" + newCar.getId())
                .build();
    }


}
