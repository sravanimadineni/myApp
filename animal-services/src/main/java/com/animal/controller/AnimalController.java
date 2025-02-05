package com.animal.controller;
import com.animal.Service.AnimalService;
import com.animal.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    // Add an animal listing
    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.addAnimal(animal));
    }

    // Fetch nearby animals
    @GetMapping("/nearby")
    public ResponseEntity<List<Animal>> getNearbyAnimals(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam int maxDistance) {
        return ResponseEntity.ok(animalService.findNearbyAnimals(latitude, longitude, maxDistance));
    }
}

