package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 1. Взимане на всички курсове
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // 2. Създаване на нов курс
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // 3. Изтриване на курс
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    // ==========================================
    // МЕТОДИ ЗА УПРАВЛЕНИЕ НА ПРЕПОДАВАТЕЛИ В КУРС
    // ==========================================

    // 4. Назначаване на преподавател към курс
    @PutMapping("/{courseId}/professor/{professorId}")
    public Course assignProfessorToCourse(@PathVariable Long courseId, @PathVariable Long professorId) {
        return courseService.assignProfessor(courseId, professorId);
    }

    // 5. Премахване на преподавател от курс
    @DeleteMapping("/{courseId}/professor")
    public Course removeProfessorFromCourse(@PathVariable Long courseId) {
        return courseService.removeProfessor(courseId);
    }
}

