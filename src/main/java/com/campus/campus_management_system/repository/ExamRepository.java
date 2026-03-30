package com.campus.campus_management_system.repository;

import com.campus.campus_management_system.model.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    
    // Намира всички изпити за конкретна специалност
    List<Exam> findByCourseId(Long courseId);
    
    // Намира всички изпити за даден преподавател (през курсовете му)
    List<Exam> findByCourseProfessorId(Long professorId);
}