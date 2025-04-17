package com.stps.notification_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Method to send a notification message to Kafka topic
    public void sendNotification(String message) {
        // Send the message to Kafka (for example, to the "itinerary-events" topic)
        kafkaTemplate.send("itinerary-events", message);
    }
}

