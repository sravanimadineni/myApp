package com.media.controller;

import com.media.dto.AnimalDto;
import com.media.dto.AnimalRequestDto;
import com.media.dto.AnimalResponseDto;
import com.media.model.Animal;
import com.media.service.AnimalService;
import com.media.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalService animalService;
    private final FileService fileService;

    public AnimalController(AnimalService animalService,
                            FileService fileService) {
        this.animalService = animalService;
        this.fileService=fileService;
    }

   /* @PostMapping
    public Animal addAnimal(
            @RequestPart Animal animal,
            @RequestPart List<MultipartFile> images,
            @RequestPart List<MultipartFile> videos) throws IOException {

        // Convert MultipartFiles to Files
        List<File> imageFiles = images.stream()
                .map(file -> {
                    try {
                        File tempFile = File.createTempFile(file.getOriginalFilename(), null);
                        file.transferTo(tempFile);
                        return tempFile;
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to process image file: " + e.getMessage());
                    }
                })
                .toList();

        List<File> videoFiles = videos.stream()
                .map(file -> {
                    try {
                        File tempFile = File.createTempFile(file.getOriginalFilename(), null);
                        file.transferTo(tempFile);
                        return tempFile;
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to process video file: " + e.getMessage());
                    }
                })
                .toList();

        return animalService.addAnimal(animal, imageFiles, videoFiles);
    }*/


    @GetMapping("/species/{species}")
    public List<Animal> getAnimalsBySpecies(@PathVariable String species) {
        return animalService.findAnimalsBySpecies(species);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createAnimal(
            @RequestPart("animal") AnimalRequestDto animalDto,
            @RequestPart("files") List<MultipartFile> files
    ) {
        try {
            Animal savedAnimal = animalService.saveAnimal(animalDto, files);
            return ResponseEntity.ok(savedAnimal);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error saving animal: " + e.getMessage());
        }
    }


    @GetMapping("/{id}/image/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable String id, @PathVariable String imageId) {
        byte[] imageData = animalService.getImage(imageId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
    }

    private AnimalResponseDto mapToResponseDto(Animal animal) {
        return new AnimalResponseDto(
                animal.getName(),
                animal.getPrice(),
                animal.getSpecies(),
                animal.getBreed()
        );
    }

    @GetMapping("/getAllAnimals")
    public ResponseEntity<List<AnimalDto>> getAllAnimals() {
        try {
            List<AnimalDto> animals = animalService.getAllAnimalsWithImages();
            return ResponseEntity.ok(animals);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileId) {
        try {
            byte[] file = fileService.getFile(fileId);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
