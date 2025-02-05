package com.media.Repository;

import com.media.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {

    List<Animal> findBySpecies(String species);

    List<Animal> findByStatus(String status);
}
