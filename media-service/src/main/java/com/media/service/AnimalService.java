package com.media.service;

import com.media.Repository.AnimalRepository;
import com.media.dto.AnimalDto;
import com.media.dto.AnimalRequestDto;
import com.media.model.Animal;
import com.mongodb.client.gridfs.model.GridFSFile;

import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    // private final S3Service s3Service;

    private final GridFsTemplate gridFsTemplate;
    private final FileService fileService;
    public AnimalService(FileService fileService,AnimalRepository animalRepository,  GridFsTemplate gridFsTemplate) {
        this.animalRepository = animalRepository;
        this.gridFsTemplate = gridFsTemplate;
        this.fileService= fileService;
    }

 /*   public Animal addAnimal(Animal animal, List<File> imageFiles, List<File> videoFiles) {
        // Upload Images
        List<String> imageUrls = imageFiles.stream()
                .map(file -> s3Service.uploadFile("images/" + file.getName(), file))
                .toList();

        // Upload Videos
        List<String> videoUrls = videoFiles.stream()
                .map(file -> s3Service.uploadFile("videos/" + file.getName(), file))
                .toList();

        // Set URLs in the animal object
        animal.setImages(imageUrls);
        animal.setVideos(videoUrls);

        return animalRepository.save(animal);
    }*/
    public Animal saveAnimal(AnimalRequestDto animalDto, List<MultipartFile> files) throws IOException {
        Animal animal = new Animal();
        animal.setName(animalDto.getName());
        animal.setAge(animalDto.getAge());
        //  animal.setBirthDate(animalDto.getBirthDate());
        animal.setSpecies(animalDto.getSpecies());
        animal.setBreed(animalDto.getBreed());
        animal.setHealth(animalDto.getHealth());
        animal.setStatus(animalDto.getStatus());
        animal.setPrice(animalDto.getPrice());
        animal.setVaccinations(animalDto.getVaccinations());

        // Upload files to GridFS
        List<String> imageIds = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileId = uploadFileToGridFs(file);
            imageIds.add(fileId);
        }
        animal.setImageIds(imageIds);

        // Save animal details to MongoDB
        return animalRepository.save(animal);
    }



    public byte[] getImage(String imageId) {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(imageId)));
        if (file == null) {
            throw new RuntimeException("File not found");
        }

        try (InputStream inputStream = gridFsTemplate.getResource(file).getInputStream()) {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file from GridFS", e);
        }
    }

    private String uploadFileToGridFs(MultipartFile file) throws IOException {
        return gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType()).toString();
    }

    public List<AnimalDto> getAllAnimalsWithImages() {
        List<Animal> animals = animalRepository.findAll();
        return animals.stream().map(this::mapToAnimalDto).toList();
    }

    private AnimalDto mapToAnimalDto(Animal animal) {
        // Fetch URLs for image IDs
        List<String> imageUrls = animal.getImageIds().stream()
                .map(fileService::getFileUrl)
                .toList();

        return new AnimalDto(
                animal.getName(),
                animal.getAge(),
                animal.getSpecies(),
                animal.getBreed(),
                animal.getHealth(),
                animal.getStatus(),
                animal.getPrice(),
                animal.getVaccinations(),
                imageUrls
        );
    }


    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public List<Animal> findAnimalsBySpecies(String species) {
        return animalRepository.findBySpecies(species);
    }
}
