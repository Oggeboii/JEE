package com.example.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.Objects;

@Setter
@Getter
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "year_model", nullable = false)
    private Year yearModel;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return getId() != null && Objects.equals(getId(), car.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "company = " + company + ", " +
                "model = " + model + ", " +
                "description = " + description + ", " +
                "yearModel = " + yearModel + ")";
    }


}
