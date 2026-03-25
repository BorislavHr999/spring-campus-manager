package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Department;
import com.campus.campus_management_system.model.entity.Professor;
import com.campus.campus_management_system.service.DepartmentService;
import com.campus.campus_management_system.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Инжектираме и Repository-то за новите бързи операции
    @Autowired
    private DepartmentRepository departmentRepository;

    // 1. Взимане на всички факултети (Твоят оригинален метод)
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // 2. Създаване на нов факултет (Защитен за Админи)
    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department, Authentication authentication) {
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        if (!isAdmin) {
            return ResponseEntity.status(403).body(Map.of("message", "Само администратори могат да добавят факултети!"));
        }

        // Използваме твоя съществуващ сървис метод
        Department savedDepartment = departmentService.createDepartment(department);
        return ResponseEntity.ok(savedDepartment);
    }

    // 3. Редакция (Добавяне на Офис) - НОВ МЕТОД
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id, @RequestBody Department department, Authentication authentication) {
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        if (!isAdmin) {
            return ResponseEntity.status(403).body(Map.of("message", "Само администратори могат да редактират факултети!"));
        }

        Optional<Department> existingOpt = departmentRepository.findById(id);
        if (existingOpt.isPresent()) {
            Department existing = existingOpt.get();
            existing.setName(department.getName());
            existing.setOffice(department.getOffice()); // Записваме офиса!
            return ResponseEntity.ok(departmentRepository.save(existing));
        } else {
            return ResponseEntity.status(404).body(Map.of("message", "Факултетът не е намерен!"));
        }
    }

    // 4. Изтриване - НОВ МЕТОД
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id, Authentication authentication) {
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        if (!isAdmin) {
            return ResponseEntity.status(403).body(Map.of("message", "Само администратори могат да трият факултети!"));
        }
        
        try {
            departmentRepository.deleteById(id);
            return ResponseEntity.ok(Map.of("message", "Факултетът е изтрит успешно!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("message", "Грешка! Вероятно има записани студенти или преподаватели в този факултет."));
        }
    }

    // 5. Зачисляване на преподавател (Твоят оригинален метод)
    @PostMapping("/{departmentId}/assign-professor/{professorId}")
    public Professor assignProfessorToDepartment(@PathVariable Long departmentId, @PathVariable Long professorId) {
        return departmentService.assignProfessorToDepartment(professorId, departmentId);
    }
}
