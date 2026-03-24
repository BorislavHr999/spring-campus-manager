package com.campus.campus_management_system.repository;

import com.campus.campus_management_system.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    boolean existsByName(String name);

    List<Course> findByProfessorId(Long professorId);
}