package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.service.ProfessorService;
import com.campus.campus_management_system.model.entity.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorService.createProfessor(professor);
    }

    @GetMapping
    public List<Professor> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    @PostMapping("/{professorId}/teach/{courseId}")
    public Course assignProfessorToCourse(@PathVariable Long professorId, @PathVariable Long courseId) {
        return professorService.assignProfessorToCourse(professorId, courseId);
    }
}
