package com.example.jpademo.jdbc;

import com.example.jpademo.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {

    @Autowired
    private JdbcTemplate template;

    private static String INSERT_QUERY = "INSERT INTO COURSE(ID, NAME, AUTHOR) VALUES(?, ?, ?)";
    private static String DELETE_QUEREY = "DELETE FROM COURSE WHERE id = ?";
    private static String SELECT_BY_ID_QUERY = "SELECT * FROM COURSE WHERE id = ?";
    private static String SELECT_ALL_QUERY = "SELECT * FROM COURSE";

    public void insert(Course course){
        template.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteById(long courseId) {
        template.update(DELETE_QUEREY, courseId);
    }

    public List<Course> findAllCourses(){
        return template.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(Course.class));
    }

    public Course findById(long id) {
        return template.queryForObject(SELECT_BY_ID_QUERY,new BeanPropertyRowMapper<Course>(Course.class), id);
    }
}
