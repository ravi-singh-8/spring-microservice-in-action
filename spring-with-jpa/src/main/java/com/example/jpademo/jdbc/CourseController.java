package com.example.jpademo.jdbc;

import com.example.jpademo.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jdbc")
public class CourseController {

    @Autowired
    private CourseRepository repository;

    @GetMapping("/courses")
    public List<Course> getCourses() {
        List<Course> courses = repository.findAllCourses();
        System.out.println(courses);
        return courses;
    }

    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable(name="id") Long courseId) {
        Course course= repository.findById(courseId);
        System.out.println(course);
        return course;
    }
}
