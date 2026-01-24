package com.campus.campus_management_system.service;

import com.campus.campus_management_system.model.entity.Department;
import com.campus.campus_management_system.model.entity.Professor;
import com.campus.campus_management_system.repository.DepartmentRepository;
import com.campus.campus_management_system.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Professor assignProfessorToDepartment(Long professorId, Long departmentId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        professor.setDepartment(department);
        return professorRepository.save(professor);
    }
}
