package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.model.entity.Exam;
import com.campus.campus_management_system.model.entity.User;
import com.campus.campus_management_system.repository.CourseRepository;
import com.campus.campus_management_system.repository.ExamRepository;
import com.campus.campus_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    // 1. Взимане на всички изпити (за Админ)
    @GetMapping
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    // 2. Взимане на изпитите за конкретна специалност (За студенти и всички останали)
    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getExamsForCourse(@PathVariable Long courseId) {
        List<Exam> exams = examRepository.findByCourseId(courseId);
        return ResponseEntity.ok(exams);
    }

    // 3. Създаване на нов изпит (Админ или Преподавателят на курса)
    @PostMapping("/course/{courseId}")
    public ResponseEntity<?> createExam(@PathVariable Long courseId, @RequestBody Exam examRequest, Authentication authentication) {
        
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean isProfessor = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_PROFESSOR"));

        if (!isAdmin && !isProfessor) {
            return ResponseEntity.status(403).body(Map.of("message", "Нямате права да насрочвате изпити!"));
        }

        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("message", "Специалността не е намерена!"));
        }
        Course course = courseOpt.get();

        // ЗАЩИТА: Ако е преподавател, проверяваме дали това е неговият курс
        if (isProfessor && !isAdmin) {
            User currentUser = userRepository.findByUsername(authentication.getName()).orElse(null);
            if (currentUser == null || currentUser.getProfessor() == null || 
                course.getProfessor() == null || 
                !course.getProfessor().getId().equals(currentUser.getProfessor().getId())) {
                return ResponseEntity.status(403).body(Map.of("message", "Можете да насрочвате изпити само за Вашите специалности!"));
            }
        }

        // Запазваме изпита
        examRequest.setCourse(course);
        Exam savedExam = examRepository.save(examRequest);
        
        return ResponseEntity.ok(savedExam);
    }

    // 4. Изтриване на изпит
    @DeleteMapping("/{examId}")
    public ResponseEntity<?> deleteExam(@PathVariable Long examId, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        // Може да се допише и логика преподавателят да трие своите изпити,
        // но засега само Админ може да ги отменя за сигурност.
        if (!isAdmin) {
            return ResponseEntity.status(403).body(Map.of("message", "Само администратор може да отменя вече насрочени изпити!"));
        }

        if (examRepository.existsById(examId)) {
            examRepository.deleteById(examId);
            return ResponseEntity.ok(Map.of("message", "Изпитът е изтрит успешно."));
        } else {
            return ResponseEntity.status(404).body(Map.of("message", "Изпитът не е намерен."));
        }
    }
}