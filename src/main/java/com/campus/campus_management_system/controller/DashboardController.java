package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final StudentRepository studentRepository;

    public DashboardController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 1. Взимане на статистиката за таблото
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        long studentCount = studentRepository.count(); 
        
        return Map.of(
            "totalStudents", studentCount,
            "activeCourses", 42
        );
    }

    // 2. Симулиране на записване (създава нов студент в базата)
    @PostMapping("/add-demo-student")
    public Map<String, String> addDemoStudent() {
        Random rand = new Random();
        int randomId = rand.nextInt(1000, 9999);

        // Създаваме реален обект спрямо твоя Entity клас
        Student student = new Student();
        student.setFirstName("Тест");
        student.setLastName("Студент " + randomId);
        student.setEmail("student" + randomId + "@campus.bg");
        student.setFacultyNumber("FAC" + randomId);
        student.setAge(20 + rand.nextInt(5));

        // Запазваме го в истинската база данни!
        studentRepository.save(student);

        return Map.of("status", "success");
    }
}