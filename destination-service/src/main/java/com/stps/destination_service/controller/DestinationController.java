package com.stps.destination_service.controller;

import com.stps.destination_service.model.Destination;
import com.stps.destination_service.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @PostMapping
    public Destination create(@RequestBody Destination destination) {
        return destinationService.enrichAndSave(destination);
    }

    @GetMapping
    public List<Destination> getAll() {
        return destinationService.findAll();
    }

    @GetMapping("/{id}")
    public Destination getById(@PathVariable Long id) {
        return destinationService.findById(id);
    }

    @PutMapping("/{id}")
    public Destination update(@PathVariable Long id, @RequestBody Destination d) {
        return destinationService.update(id, d);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        destinationService.deleteById(id);
    }
}
