package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final StudentRepository studentRepository;

    public DashboardController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        long studentCount = studentRepository.count(); 
        
        return Map.of(
            "totalStudents", studentCount,
            "activeCourses", 42
        );
    }
}