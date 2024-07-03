package com.example.serving_web_content.controller;

import com.example.serving_web_content.PersonService;
import com.example.serving_web_content.dto.Message;
import com.example.serving_web_content.dto.Person;
import com.example.serving_web_content.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonService personService;

    @GetMapping
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<Person>(repository.save(person), status);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }

    @GetMapping("/{person_id}/message")
    public ResponseEntity<?> getMessage(@PathVariable int person_id) {
        Optional<Person> personOptional = repository.findById(person_id);
        if (personOptional.isPresent()) {
            HttpStatus status = HttpStatus.OK;
            return new ResponseEntity<List<Message>>(personOptional.get().getMessages(), status);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{person_id}/message/{message_id}")
    public ResponseEntity<?> getMessageById(@PathVariable int person_id, @PathVariable int message_id)
    {
        Optional<Person> personOptional = repository.findById(person_id);
        if (personOptional.isPresent()) {
            HttpStatus status = HttpStatus.OK;
            return new ResponseEntity<Message>(personOptional.get().getMessage(message_id), status);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{person_id}/message")
    public Person addMessage(@PathVariable int person_id, @RequestBody Message message) {
        return personService.addMessageToPerson(person_id, message);
    }

    @DeleteMapping("/{person_id}/message/{message_id}")
    public ResponseEntity<?> deleteMessage(@PathVariable int person_id, @PathVariable int message_id) {
        Optional<Person> personOptional = repository.findById(person_id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            if (person.getMessages().removeIf(
                    message -> message.getId() == message_id)) {
                repository.save(person);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build(); // 404
    }
}
