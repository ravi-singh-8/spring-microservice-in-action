package com.example.jpademo.springdata;

import com.example.jpademo.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spring-data/")
public class SpringDataCourseController {

    @Autowired
    private SpringDataCourseRepository repository;

    @GetMapping("courses")
    public List<Course> getAllCourses(){
        return repository.findAll();
    }

    @GetMapping("courses/{id}")
    public Course getCourseById(@PathVariable(name="id") long id) {
        return repository.findById(id).get();
    }

    @GetMapping("courses/byAuthor/{byAuthor}")
    public List<Course> getCourseByAuthor(@PathVariable(name = "byAuthor") String author) {
        return repository.findByAuthor(author);
    }
}
