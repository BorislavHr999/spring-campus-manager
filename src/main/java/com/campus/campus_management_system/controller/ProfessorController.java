package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.model.entity.Professor;
import com.campus.campus_management_system.repository.CourseRepository;
import com.campus.campus_management_system.repository.ProfessorRepository;
import com.campus.campus_management_system.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Взимане на всички преподаватели
    @GetMapping
    public List<Professor> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    // Добавяне на нов преподавател (Защитено за Админ)
    @PostMapping
    public ResponseEntity<?> createProfessor(@RequestBody Professor professor, Authentication authentication) {
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        if (!isAdmin) {
            return ResponseEntity.status(403).body(Map.of("message", "Само администратори могат да добавят преподаватели!"));
        }

        return ResponseEntity.ok(professorService.createProfessor(professor));
    }

    // --- НОВ МЕТОД: Редактиране на преподавател (Защитено за Админ) ---
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfessor(@PathVariable Long id, @RequestBody Professor updatedProfessor, Authentication authentication) {
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        if (!isAdmin) {
            return ResponseEntity.status(403).body(Map.of("message", "Само администратори могат да редактират преподаватели!"));
        }

        Optional<Professor> profOpt = professorRepository.findById(id);
        if (profOpt.isPresent()) {
            Professor existingProf = profOpt.get();
            existingProf.setFirstName(updatedProfessor.getFirstName());
            existingProf.setLastName(updatedProfessor.getLastName());
            existingProf.setTitle(updatedProfessor.getTitle());
            
            // ТУК ЗАПАЗВАМЕ ИМЕЙЛА!
            existingProf.setEmail(updatedProfessor.getEmail()); 
            
            professorRepository.save(existingProf);
            return ResponseEntity.ok(existingProf);
        } else {
            return ResponseEntity.status(404).body(Map.of("message", "Преподавателят не е намерен!"));
        }
    }

    // Изтриване на преподавател (с чистене на връзките в базата)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfessor(@PathVariable Long id, Authentication authentication) {
        
        // 1. Проверка дали потребителят е АДМИН
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        if (!isAdmin) {
            return ResponseEntity.status(403).body(Map.of("message", "Само администратори могат да трият преподаватели!"));
        }

        try {
            Optional<Professor> profOpt = professorRepository.findById(id);
            if (profOpt.isPresent()) {
                // 2. Намираме всички курсове, които този професор води
                List<Course> courses = courseRepository.findByProfessorId(id);
                
                // 3. Правим професора на NULL за тези курсове (освобождаваме ги)
                for (Course course : courses) {
                    course.setProfessor(null);
                    courseRepository.save(course);
                }

                // 4. Вече можем безопасно да изтрием професора
                professorRepository.delete(profOpt.get());

                return ResponseEntity.ok(Map.of("message", "Преподавателят е изтрит успешно!"));
            } else {
                return ResponseEntity.status(404).body(Map.of("message", "Преподавателят не е намерен!"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Грешка при изтриване: " + e.getMessage()));
        }
    }
}
