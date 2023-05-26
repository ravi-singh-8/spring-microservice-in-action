package com.example.jpademo.springdata;

import com.example.jpademo.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataCourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByAuthor(String author);
}
