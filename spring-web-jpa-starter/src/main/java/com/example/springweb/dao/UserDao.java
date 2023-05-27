package com.example.springweb.dao;

import com.example.springweb.entities.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao {
    private static List<User> users = new ArrayList<>(List.of(
            new User(1L, "Adam", LocalDate.now().minusYears(30)),
            new User(2L, "Eve", LocalDate.now().minusYears(25)),
            new User(3L, "Jim", LocalDate.now().minusYears(20))
    ));

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public List<User> findAll() {
        return users;
    }

    public User addUser(User user) {
        user.setId(Long.valueOf(users.size()+1));
        users.add(user);
        return users.get(users.size()-1);
    }

    public User deleteUser(long userId) {
        Optional<User> toDelete = findById(userId);
        if(toDelete.isPresent()) users.remove(toDelete.get());
        return toDelete.get();
    }
}
