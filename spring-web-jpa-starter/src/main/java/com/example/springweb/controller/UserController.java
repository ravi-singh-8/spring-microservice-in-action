package com.example.springweb.controller;

import com.example.springweb.entities.User;
import com.example.springweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return service.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable(name="id") long id) {
         return service.getUser(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody @Validated User user) {
        User savedUser = service.addUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
        User deletedUser = service.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedUser);
    }
}
