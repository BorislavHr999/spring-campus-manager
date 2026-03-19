package com.campus.campus_management_system.repository;

import com.campus.campus_management_system.model.entity.Enrollment;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

@Query("SELECT e.course.name, COUNT(e) FROM Enrollment e GROUP BY e.course.name")
List<Object[]> countStudentsPerCourse();
}



