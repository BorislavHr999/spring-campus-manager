package com.campus.campus_management_system.service;

import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List <Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
