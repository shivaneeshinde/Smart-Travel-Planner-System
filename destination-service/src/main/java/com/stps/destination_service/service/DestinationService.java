package com.stps.destination_service.service;

import com.stps.destination_service.model.Destination;
import com.stps.destination_service.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    public Destination enrichAndSave(Destination destination) {
        // Fetch weather info (dummy placeholder)
        String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + destination.getCity() + "&appid=YOUR_API_KEY&units=metric";
        try {
            var weatherResponse = restTemplate.getForObject(weatherApiUrl, String.class);
            destination.setWeather(weatherResponse != null ? "Sample Weather Info" : "N/A");
        } catch (Exception e) {
            destination.setWeather("Unavailable");
        }

        // Fetch country info
        String countryApiUrl = "https://restcountries.com/v3.1/name/" + destination.getCountry();
        try {
            var countryInfo = restTemplate.getForObject(countryApiUrl, String.class);
            destination.setCountryInfo(countryInfo != null ? "Sample Country Info" : "N/A");
        } catch (Exception e) {
            destination.setCountryInfo("Unavailable");
        }

        return repository.save(destination);
    }

    public List<Destination> findAll() { return repository.findAll(); }
    public Destination findById(Long id) { return repository.findById(id).orElse(null); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Destination update(Long id, Destination d) {
        d.setId(id);
        return enrichAndSave(d);
    }
}
