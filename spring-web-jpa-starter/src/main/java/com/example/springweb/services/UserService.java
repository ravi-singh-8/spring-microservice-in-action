package com.example.springweb.services;

import com.example.springweb.controller.UserNotFoundException;
import com.example.springweb.dao.UserDao;
import com.example.springweb.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    public User getUser(Long id) {
        Optional<User> opUser = dao.findById(id);
        if(opUser.isPresent()){
            return opUser.get();
        }

        throw new UserNotFoundException("User with id: " + id + ", not found");
    }

    public User deleteUser(Long id) {
        Optional<User> opUser = dao.findById(id);
        if(opUser.isPresent()){
            return dao.deleteUser(id);
        }

        throw new UserNotFoundException("User with id: " + id + ", not found");
    }

    public List<User> getUsers() {
        return dao.findAll();
    }

    public User addUser(User user) {
        return dao.addUser(user);
    }
}
