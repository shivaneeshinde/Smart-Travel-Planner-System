package com.stps.itinerary_service.controller;

import com.stps.itinerary_service.model.Itinerary;
import com.stps.itinerary_service.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itineraries")
class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    @PostMapping
    public Itinerary create(@RequestBody Itinerary itinerary) {
        return itineraryService.createItinerary(itinerary);
    }

    @GetMapping
    public List<Itinerary> getAll() {
        return itineraryService.getAll();
    }

    @GetMapping("/{id}")
    public Itinerary getById(@PathVariable Long id) {
        return itineraryService.getById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Itinerary update(@PathVariable Long id, @RequestBody Itinerary itinerary) {
        return itineraryService.updateItinerary(id, itinerary);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itineraryService.deleteItinerary(id);
    }
}
