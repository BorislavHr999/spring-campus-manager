package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Student;
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

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        long studentCount = studentRepository.count(); 
        long courseCount = courseRepository.count(); 
        
        return Map.of(
            "totalStudents", studentCount,
            "activeCourses", courseCount
        );
    }

    @PostMapping("/add-demo-student")
    public Map<String, String> addDemoStudent() {
        Random rand = new Random();
        int randomId = rand.nextInt(1000, 9999);

        Student student = new Student();
        student.setFirstName("Тест");
        student.setLastName("Студент " + randomId);
        student.setEmail("student" + randomId + "@campus.bg");
        student.setFacultyNumber("FAC" + randomId);
        student.setAge(20 + rand.nextInt(5));

        studentRepository.save(student);

        return Map.of("status", "success");
    }

    @GetMapping("/latest-students")
    public List<Student> getLatestStudents() {
        return studentRepository.findTop5ByOrderByIdDesc();
    }

    @DeleteMapping("/delete-student/{id}")
    public Map<String, String> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return Map.of("status", "deleted");
        }
        return Map.of("status", "error");
    }

    @GetMapping("/all-students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}