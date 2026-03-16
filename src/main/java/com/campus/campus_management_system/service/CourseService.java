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

    // НОВО: Инжектираме хранилището за преподаватели, за да ги намираме в базата
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
    // НОВИ МЕТОДИ ЗА УПРАВЛЕНИЕ НА ПРЕПОДАВАТЕЛИ
    // ==========================================

    // Назначаване на преподавател към курс
    public Course assignProfessor(Long courseId, Long professorId) {
        // 1. Намираме курса
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курсът не е намерен с ID: " + courseId));
        
        // 2. Намираме преподавателя
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Преподавателят не е намерен с ID: " + professorId));
        
        // 3. Закачаме преподавателя към курса
        course.setProfessor(professor);
        
        // 4. Запазваме обновения курс в базата
        return courseRepository.save(course);
    }

    // Премахване на преподавател от курс
    public Course removeProfessor(Long courseId) {
        // 1. Намираме курса
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курсът не е намерен с ID: " + courseId));
        
        // 2. Разкачаме преподавателя (правим го null)
        course.setProfessor(null);
        
        // 3. Запазваме промяната
        return courseRepository.save(course);
    }

    public Course createCourse(Course course){
        if (courseRepository.existsByName(course.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Специалност с това име вече съществува!");

     }
       return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course updatedData) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Курсът не е намерен!"));

        // Проверяваме дали не се опитваме да сложим име, което вече е заето от друг курс
        if (!existingCourse.getName().equals(updatedData.getName()) && courseRepository.existsByName(updatedData.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Специалност с това име вече съществува!");
        }

        existingCourse.setName(updatedData.getName());
        existingCourse.setCredits(updatedData.getCredits());

        return courseRepository.save(existingCourse);
    }
    
}
