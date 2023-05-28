package com.example.springweb.services;

import com.example.springweb.controller.UserNotFoundException;
import com.example.springweb.entities.Post;
import com.example.springweb.entities.User;
import com.example.springweb.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUser(Long id) {
        Optional<User> opUser = repository.findById(id);
        if(opUser.isPresent()){
            return opUser.get();
        }

        throw new UserNotFoundException("User with id: " + id + ", not found");
    }

    public User deleteUser(Long id) {
        Optional<User> opUser = repository.findById(id);
        if(opUser.isPresent()){
            User toBeDeleted = opUser.get();
            repository.delete(toBeDeleted);
            return toBeDeleted;
        }

        throw new UserNotFoundException("User with id: " + id + ", not found");
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User addUser(User user) {
        return repository.save(user);
    }

    public List<Post> getAllPost(long userId){
         User user = getUser(userId);
         return user.getPosts();
    }


}
