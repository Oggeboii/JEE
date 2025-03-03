package entity;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Car {
   @Id
   @Column(name = "id", nullable = false)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "make", nullable = false)
   private String company;

   @Column(name = "model", nullable = false)
   private String model;

   @Column(name = "description", nullable = false)
   private String description;

   @Column(name = "year model", nullable = false)
   private Long yearModel;





}
