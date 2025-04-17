package com.stps.itinerary_service.repository;

import com.stps.itinerary_service.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
}
