package com.example.springweb.entities;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class User {

    @Positive
    private Long id;

    @Size(min=2, max=25)
    private String name;

    @Past
    private LocalDate birthDate;

    public User() {
    }

    public User(Long id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User[" + "id=" + id + ", name='" + name + '\'' + ", birthDate=" + birthDate + "]";
    }
}
