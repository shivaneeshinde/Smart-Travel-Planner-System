package com.stps.itinerary_service.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    @ElementCollection
    private List<Long> destinationIds;

    @ElementCollection
    private List<String> activities;

    private LocalDate startDate;
    private LocalDate endDate;
}
