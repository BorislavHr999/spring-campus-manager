package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Department;
import com.campus.campus_management_system.model.entity.Professor;
import com.campus.campus_management_system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/{departmentId}/assign-professor/{professorId}")
    public Professor assignProfessorToDepartment(@PathVariable Long departmentId, @PathVariable Long professorId) {
        return departmentService.assignProfessorToDepartment(professorId, departmentId);
    }
}
