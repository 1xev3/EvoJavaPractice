package com.example.person.controller;

import com.example.person.model.Person;
import com.example.person.model.Weather;
import com.example.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.weather}")
    private String apiWeatherUrl;

    @GetMapping
    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable int id,
                                            @RequestBody Person newPerson) {
        Optional<Person> result = repository.findById(id);

        if (result.isPresent()) {
            Person existingPerson = result.get();
            existingPerson.setName(newPerson.getName());
            existingPerson.setLocation(newPerson.getLocation());

            Person updatedLocation = repository.save(existingPerson);
            return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id)
    {
        Optional<Person> person2del=repository.findById(id);
        if( person2del.isPresent()) {
            repository.deleteById(person2del.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/weather")
    public ResponseEntity<Weather> getWeather(@PathVariable int id) {
        if (repository.existsById(id)) {
            String location = repository.findById(id).get().getLocation();
            Weather weather = restTemplate.getForObject(apiWeatherUrl + "/weather?location=" + location, Weather.class);
            return new ResponseEntity<>(weather, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Person person) {
        return repository.findById(person.getId()).isPresent()
                ? new ResponseEntity<>(repository.findById(person.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(repository.save(person), HttpStatus.CREATED);
    }
}