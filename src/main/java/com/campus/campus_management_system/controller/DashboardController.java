package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.model.entity.Course; // Внасяме модела Course
import com.campus.campus_management_system.repository.StudentRepository;
import com.campus.campus_management_system.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public DashboardController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // --- МЕТОДИ ЗА ТАБЛОТО И СТУДЕНТИТЕ ---

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return Map.of(
            "totalStudents", studentRepository.count(),
            "activeCourses", courseRepository.count()
        );
    }

    @PostMapping("/add-demo-student")
    public Map<String, String> addDemoStudent() {
        Random rand = new Random();
        Student student = new Student();
        student.setFirstName("Тест");
        student.setLastName("Студент " + rand.nextInt(1000, 9999));
        student.setEmail("student" + rand.nextInt(1000, 9999) + "@campus.bg");
        student.setFacultyNumber("FAC" + rand.nextInt(1000, 9999));
        student.setAge(20 + rand.nextInt(5));
        studentRepository.save(student);
        return Map.of("status", "success");
    }

    @GetMapping("/latest-students")
    public List<Student> getLatestStudents() {
        return studentRepository.findTop5ByOrderByIdDesc();
    }

    @GetMapping("/all-students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @DeleteMapping("/delete-student/{id}")
    public Map<String, String> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return Map.of("status", "deleted");
        }
        return Map.of("status", "error");
    }

    // --- НОВИ МЕТОДИ ЗА КУРСОВЕТЕ (СПЕЦИАЛНОСТИ) ---

    @GetMapping("/all-courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/add-demo-course")
    public Map<String, String> addDemoCourse() {
        String[] subjects = {"Основи на Java", "Spring Boot Masterclass", "DevOps Практики", "Алгоритми", "Бази данни"};
        Random rand = new Random();
        
        Course course = new Course();
        // Генерираме име от масива + случайно число за уникалност
        course.setName(subjects[rand.nextInt(subjects.length)] + " " + rand.nextInt(100, 999));
        course.setCredits(rand.nextInt(2, 10)); // Кредити между 2 и 9
        // Оставяме Professor null за сега
        
        courseRepository.save(course);
        return Map.of("status", "success");
    }

    @DeleteMapping("/delete-course/{id}")
    public Map<String, String> deleteCourse(@PathVariable Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return Map.of("status", "deleted");
        }
        return Map.of("status", "error");
    }
}