package com.stps.notification_service.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaNotificationListener {

    // Listening to the "itinerary-events" topic for messages
    @KafkaListener(topics = "itinerary-events", groupId = "notification-service")
    public void listen(String message) {
        // Log the event (you could replace this with actual email/SMS logic)
        System.out.println("Received notification: " + message);
    }
}
