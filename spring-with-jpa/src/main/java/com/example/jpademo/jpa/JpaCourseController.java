package com.example.jpademo.jpa;

import com.example.jpademo.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("jpa/")
public class JpaCourseController {

    @Autowired
    private CourseJpaRepository repository;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    @GetMapping("/courses/{id}")
    public Course getById(@PathVariable(name="id") long id) {
        return repository.findById(id);
    }
}
