package com.example.location.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Geodata {
    @Id
    @GeneratedValue
    private int id;

    @NonNull private String name;
    @NonNull private Double lat;
    @NonNull private Double lon;
}