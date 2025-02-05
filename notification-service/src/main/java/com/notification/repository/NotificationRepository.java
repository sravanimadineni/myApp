package com.notification.repository;

import com.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    boolean existsByUserIdAndListingId(int userId, String listingId);
}

