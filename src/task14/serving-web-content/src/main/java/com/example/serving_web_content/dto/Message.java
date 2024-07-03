package com.example.serving_web_content.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String text;
    private LocalDate time;

    @ManyToOne
    @JsonIgnore
    private Person person;

    public Message() {
    }

    public Message(String title, String text, LocalDate time, Person person) {
        this.title = title;
        this.text = text;
        this.time = time;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}