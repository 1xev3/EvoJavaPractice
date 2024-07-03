package com.example.serving_web_content;

import com.example.serving_web_content.dto.Message;
import com.example.serving_web_content.dto.Person;
import com.example.serving_web_content.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    public Person addMessageToPerson(int personId, Message message) {
        Person person = repository.findById(personId).get();
        message.setPerson(person);
        message.setTime(LocalDate.now());
        person.addMessage(message);
        return repository.save(person);
    }
}