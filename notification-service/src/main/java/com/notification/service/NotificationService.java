package com.notification.service;

import com.notification.model.Animal;
import com.notification.model.Notification;
import com.notification.model.User;
import com.notification.model.UserPreferences;
import com.notification.repository.AnimalRepository;
import com.notification.repository.NotificationRepository;
import com.notification.repository.UserPreferencesRepository;
import com.notification.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final UserRepository userRepository;
    private final UserPreferencesRepository preferencesRepository;
    private final AnimalRepository animalRepository;
    private final NotificationRepository notificationRepository;

    public NotificationService(UserRepository userRepository, UserPreferencesRepository preferencesRepository,
                               AnimalRepository listingRepository, NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.preferencesRepository = preferencesRepository;
        this.animalRepository = listingRepository;
        this.notificationRepository = notificationRepository;
    }

    @Scheduled(fixedRate = 300000) // Runs every 5 minutes
    public void checkForMatches() {
        List<UserPreferences> preferences = preferencesRepository.findAll();

        for (UserPreferences preference : preferences) {
            User user = userRepository.findById(preference.getUserId()).orElse(null);
            if (user == null || !user.isActive()) continue;

            double[] userCoordinates = { /* Retrieve from user profile or default */ };

            List<Animal> matchingListings = animalRepository.findNearbyListings(
                    preference.getSpecies(),
                    preference.getBreed(),
                    userCoordinates,
                    preference.getMaxDistance()
            );

            for (Animal listing : matchingListings) {
                if (!notificationRepository.existsByUserIdAndListingId(user.getUserId(), listing.getId())) {
                    sendNotification(user, listing);
                }
            }
        }
    }

    private void sendNotification(User user, Animal listing) {
        String message = String.format("A %s (%s) is available nearby.", listing.getType(), listing.getBreed());

        Notification notification = new Notification();
        notification.setUserId(user.getUserId());
        notification.setListingId(listing.getId());
        notification.setMessage(message);
        notificationRepository.save(notification);

        System.out.printf("Notification sent to %s: %s%n", user.getEmail(), message);
    }
}
