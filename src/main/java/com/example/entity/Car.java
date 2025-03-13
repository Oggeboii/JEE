package com.example.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Company is required")
    @Column(name = "company", nullable = false)
    private String company;

    @NotBlank(message = "Model i required")
    @Column(name = "model", nullable = false)
    private String model;

    @Size(max = 1000)
    @Column(name = "description", nullable = false)
    private String description;

    @PastOrPresent(message = "year model must be in past or present")
    @Column(name = "year_model", nullable = false)
    private Year yearModel;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]{3}\\d{3}$", message = "license number must be 3 letters and 3 digits")
    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;


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
                "yearModel = " + yearModel + "," +
                "licenseNumber" + licenseNumber + ")";
    }


}
