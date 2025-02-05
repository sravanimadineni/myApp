package com.notification.repository;


import com.notification.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {

    @Query("{ $and: [ { type: ?0 }, { breed: ?1 }, { location: { $near: { $geometry: { type: 'Point', coordinates: ?2 }, $maxDistance: ?3 } } } ] }")
    List<Animal> findNearbyListings(String type, String breed, double[] coordinates, int maxDistance);
}

