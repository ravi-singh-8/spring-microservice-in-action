package com.example.springweb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity(name= "POST")
public class Post {

    @Id
    @GeneratedValue

    @Column(name="POST_ID")
    private long id;

    @NotNull
    @Column(name="POST_MESSAGE")
    @JsonProperty("message")
    private String message;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Post() {
    }

    public Post(long id, String message, User user) {
        this.id = id;
        this.message = message;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
