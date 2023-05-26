package com.example.jpademo;

import com.example.jpademo.models.Course;
import com.example.jpademo.jdbc.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1, "Learn AWS", "Ranga"));
        repository.insert(new Course(2, "Learn Spring", "Ranga"));
        repository.insert(new Course(3, "Learn Microservices", "Ranga"));
    }
}
