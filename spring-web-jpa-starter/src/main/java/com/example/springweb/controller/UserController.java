package com.example.springweb.controller;

import com.example.springweb.entities.Post;
import com.example.springweb.entities.User;
import com.example.springweb.services.PostService;
import com.example.springweb.services.UserService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

    private UserService userService;
    private PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable(name="id") long id) {
        User user = userService.getUser(id);
        //EntityModel and WebMvcLinkBuilder is for HATEOAS
        EntityModel<User> userEntityModel = EntityModel.of(user);
        WebMvcLinkBuilder builder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        userEntityModel.add(builder.withRel("all-users"));
        return userEntityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody @Validated User user) {
        User savedUser = userService.addUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
        User deletedUser = userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedUser);
    }

    @GetMapping("users/{id}/posts")
    public List<Post> getPosts(@PathVariable(name="id") long userId) {
        List<Post> posts = userService.getAllPost(userId);
        return posts;
    }

    @PostMapping("users/{id}/posts")
    public ResponseEntity<Object> addPost(@PathVariable(name="id") long userId, @RequestBody @Validated Post post) {
        User user = userService.getUser(userId);
        post.setUser(user);
        Post savedPost = postService.savePost(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
