package com.campus.campus_management_system.repository;

import com.campus.campus_management_system.model.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("SELECT COUNT(e) > 0 FROM Enrollment e WHERE e.student.id = :studentId AND e.course.id = :courseId")
    boolean existsByStudentIdAndCourseId(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

    @Query("SELECT e.course.name, COUNT(e) FROM Enrollment e GROUP BY e.course.name")
    List<Object[]> countStudentsPerCourse();

    List<Enrollment> findByStudentId(long studentId);

    java.util.Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @org.springframework.transaction.annotation.Transactional
    @org.springframework.data.jpa.repository.Modifying
    void deleteByStudentId(Long studentId);

    List<Enrollment> findByCourseId(Long courseId);
}



