package com.media.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalRequestDto {

    private String name;          // Name of the animal
    private int age;              // Age in years
    private String birthDate;     // Birth date
    private String species;       // Animal species (e.g., Dog, Cat)
    private String breed;         // Breed of the animal
    private String health;        // Health status
    private String status;        // Status: For Sale, Adoption, Care
    private double price;         // Price for sale
    private List<String> vaccinations; // List of vaccinations
}

