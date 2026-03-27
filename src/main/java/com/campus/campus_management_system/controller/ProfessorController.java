package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.model.entity.Professor;
import com.campus.campus_management_system.model.entity.User;
import com.campus.campus_management_system.repository.CourseRepository;
import com.campus.campus_management_system.repository.ProfessorRepository;
import com.campus.campus_management_system.repository.UserRepository;
import com.campus.campus_management_system.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    // --- НОВИ ЗАВИСИМОСТИ ЗА СЪЗДАВАНЕ НА АКАУНТ ---
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        try {
            // 1. Проверка дали вече няма такъв потребител (по имейл/потребителско име)
            if (professor.getEmail() == null || professor.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Имейлът е задължителен за създаване на акаунт!"));
            }
            if (userRepository.findByUsername(professor.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Вече съществува акаунт с този имейл!"));
            }

            // 2. Създаване на User акаунт за преподавателя
            User newUser = User.builder()
                    .username(professor.getEmail()) // Използваме имейла за логин
                    .password(passwordEncoder.encode("prof123")) // Парола по подразбиране
                    .role("ROLE_PROFESSOR") // НОВАТА РОЛЯ!
                    .build();
            
            // Запазваме акаунта в базата
            User savedUser = userRepository.save(newUser);

            // 3. Закачаме акаунта към профила на преподавателя
            professor.setUser(savedUser);

            // 4. Запазваме преподавателя
            Professor savedProf = professorService.createProfessor(professor);
            
            return ResponseEntity.ok(savedProf);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Грешка при създаване: " + e.getMessage()));
        }
    }

    // Редактиране на преподавател (Защитено за Админ)
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
        
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        if (!isAdmin) {
            return ResponseEntity.status(403).body(Map.of("message", "Само администратори могат да трият преподаватели!"));
        }

        try {
            Optional<Professor> profOpt = professorRepository.findById(id);
            if (profOpt.isPresent()) {
                Professor profToDelete = profOpt.get();

                // 2. Намираме всички курсове, които този професор води
                List<Course> courses = courseRepository.findByProfessorId(id);
                
                // 3. Правим професора на NULL за тези курсове (освобождаваме ги)
                for (Course course : courses) {
                    course.setProfessor(null);
                    courseRepository.save(course);
                }

                // Взимаме свързания User (ако има такъв)
                User linkedUser = profToDelete.getUser();

                // 4. Изтриваме професора
                professorRepository.delete(profToDelete);

                // 5. Изтриваме и акаунта му за вход, за да не остава излишен (orphan) запис
                if (linkedUser != null) {
                    userRepository.delete(linkedUser);
                }

                return ResponseEntity.ok(Map.of("message", "Преподавателят е изтрит успешно!"));
            } else {
                return ResponseEntity.status(404).body(Map.of("message", "Преподавателят не е намерен!"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Грешка при изтриване: " + e.getMessage()));
        }
    }
}
