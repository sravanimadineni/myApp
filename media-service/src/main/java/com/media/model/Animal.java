package com.media.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Document(collection = "animals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    private String id;

    private String name;          // Name of the animal
    private int age;              // Age in years
    private String birthDate;     // Birth date
    private String species;       // Animal species (e.g., Dog, Cat)
    private String breed;         // Breed of the animal
    private String health;        // Health status
    private String status;        // Status: For Sale, Adoption, Care
    private double price;         // Price for sale

    private List<String> vaccinations; // List of vaccinations

    private List<String> images;  // URLs of uploaded images
    private List<String> videos;  // URLs of uploaded videos

    private List<String> imageIds; // GridFS IDs for images (if stored in GridFS)
    private List<String> videoIds; // GridFS IDs for videos (if stored in GridFS)
}
