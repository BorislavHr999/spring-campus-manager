package com.campus.campus_management_system.service;

import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.model.entity.Professor;
import com.campus.campus_management_system.repository.CourseRepository;
import com.campus.campus_management_system.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    // Взимане на всички курсове
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Изтриване на курс
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    // ==========================================
    // МЕТОДИ ЗА УПРАВЛЕНИЕ НА ПРЕПОДАВАТЕЛИ
    // ==========================================

    public Course assignProfessor(Long courseId, Long professorId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курсът не е намерен с ID: " + courseId));
        
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Преподавателят не е намерен с ID: " + professorId));
        
        course.setProfessor(professor);
        return courseRepository.save(course);
    }

    public Course removeProfessor(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курсът не е намерен с ID: " + courseId));
        
        course.setProfessor(null);
        return courseRepository.save(course);
    }

    // СЪЗДАВАНЕ НА КУРС (с проверка за преподавател)
    public Course createCourse(Course course) {
        if (courseRepository.existsByName(course.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Специалност с това име вече съществува!");
        }

        // НОВО: Ако при създаването е избран преподавател, го връзваме правилно
        if (course.getProfessor() != null && course.getProfessor().getId() != null) {
            Professor professor = professorRepository.findById(course.getProfessor().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Преподавателят не е намерен!"));
            course.setProfessor(professor);
        } else {
            course.setProfessor(null);
        }

        return courseRepository.save(course);
    }

    // ОБНОВЯВАНЕ НА КУРС (вече не губи преподавателя!)
    public Course updateCourse(Long id, Course updatedData) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Курсът не е намерен!"));

        if (!existingCourse.getName().equals(updatedData.getName()) && courseRepository.existsByName(updatedData.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Специалност с това име вече съществува!");
        }

        // Презаписваме основните данни
        existingCourse.setName(updatedData.getName());
        existingCourse.setCredits(updatedData.getCredits());

        // НОВО: Презаписваме и преподавателя!
        if (updatedData.getProfessor() != null && updatedData.getProfessor().getId() != null) {
            Professor professor = professorRepository.findById(updatedData.getProfessor().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Преподавателят не е намерен!"));
            existingCourse.setProfessor(professor);
        } else {
            // Ако в падащото меню е избрано "-- Изберете --", махаме преподавателя
            existingCourse.setProfessor(null);
        }

        return courseRepository.save(existingCourse);
    }
}