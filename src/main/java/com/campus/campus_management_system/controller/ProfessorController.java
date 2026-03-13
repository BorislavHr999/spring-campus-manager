package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Professor;
import com.campus.campus_management_system.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    // Взимане на всички преподаватели
    @GetMapping
    public List<Professor> getAllProfessors() {
        return professorService.getAllProfessors(); // Увери се, че методът в Service се казва така
    }

    // Добавяне на нов преподавател
    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorService.createProfessor(professor);
    }

    // Изтриване на преподавател
    @DeleteMapping("/{id}")
    public void deleteProfessor(@PathVariable Long id) {
    }
};
