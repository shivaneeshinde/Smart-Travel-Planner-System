package com.stps.scheduled_sync_service.service;

import com.stps.scheduled_sync_service.publisher.KafkaPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScheduledSyncService {

    private final KafkaPublisher kafkaPublisher;
    private final RestTemplate restTemplate;
    private final String destinationApiUrl = "https://restcountries.com/v3.1/all"; // External API to sync data

    public ScheduledSyncService(KafkaPublisher kafkaPublisher, RestTemplate restTemplate) {
        this.kafkaPublisher = kafkaPublisher;
        this.restTemplate = restTemplate;
    }

    // Scheduled task to refresh destination info every 6 hours
    @Scheduled(cron = "0 0 */6 * * *")
    public void syncDestinationData() {
        // Call external API to get fresh destination data
        String response = restTemplate.getForObject(destinationApiUrl, String.class);

        // Here you can process and update destination data

        // After data sync, publish event to Kafka
        kafkaPublisher.publish("Destination data updated");
    }

    // Scheduled task to clean up old itinerary entries (example)
    @Scheduled(cron = "0 0 0 * * *")
    public void cleanupOldItineraries() {
        // Logic to clean up old itineraries
        System.out.println("Cleaning up old itineraries...");
    }
}


//@Service
//public class ScheduledSyncService {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final RestTemplate restTemplate;
//
//    public ScheduledSyncService(KafkaTemplate<String, String> kafkaTemplate, RestTemplate restTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//        this.restTemplate = restTemplate;
//    }
//
//    // Every 6 hours, sync destination data from an external API
//    @Scheduled(fixedRate = 21600000) // 6 hours in milliseconds
//    public void syncDestinationData() {
//        // Fetch external data (e.g., from REST Countries API)
//        String response = restTemplate.getForObject("https://restcountries.com/v3.1/all", String.class);
//        // Here you can process and update destinations with new data
//        // For simplicity, we’ll just log it and publish an event
//
//        // Publishing the event to Kafka topic
//        kafkaTemplate.send("travel-updates", "Destination data refreshed");
//
//        System.out.println("Destination data refreshed: " + response);
//    }
//
//    // Optional: Clean up old itineraries (if applicable)
//    @Scheduled(cron = "0 0 0 * * ?") // Run every midnight
//    public void cleanOldItineraries() {
//        // Cleanup logic here
//        System.out.println("Old itineraries cleaned.");
//    }
//}