package com.example.location.controller;

import com.example.location.model.Geodata;
import com.example.location.model.Weather;
import com.example.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.weather}")
    private String apiWeatherUrl;

    @GetMapping
    public ResponseEntity<?> getLocation(@RequestParam(required = false) String name) {
        if (name == null) {
            return new ResponseEntity<Iterable<Geodata>>(repository.findAll(), HttpStatus.OK);
        }

        Optional<Geodata> result = repository.findByName(name);
        if (result.isPresent()) {
            return new ResponseEntity<Optional<Geodata>>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/weather")
    public ResponseEntity<?> redirectRequestWeather(@RequestParam String name) {
        Optional<Geodata> geodata = repository.findByName(name);
        if (geodata.isPresent()) {
            Geodata existingLocation = geodata.get();
            String url = String.format("%s/weather?lat=%s&lon=%s",
                    apiWeatherUrl,
                    existingLocation.getLat(),
                    existingLocation.getLon()
            );
            return new ResponseEntity<>(
                    restTemplate.getForObject(url, Weather.class),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping
    public ResponseEntity<?> updateLocation(@RequestParam String name,
                                            @RequestBody Geodata location) {
        Optional<Geodata> result = repository.findByName(name);

        if (result.isPresent()) {
            Geodata existingLocation = result.get();
            existingLocation.setName(location.getName());
            existingLocation.setLat(location.getLat());
            existingLocation.setLon(location.getLon());

            Geodata updatedLocation = repository.save(existingLocation);
            return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLocation(@RequestParam String name) {
        Optional<Geodata> result = repository.findByName(name);

        if (result.isPresent()) {
            repository.delete(result.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Geodata location) {
        return repository.findById(location.getId()).isPresent()
                ? new ResponseEntity<>(repository.findById(location.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(repository.save(location), HttpStatus.CREATED);
    }
}
