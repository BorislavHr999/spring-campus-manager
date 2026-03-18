package com.campus.campus_management_system.service;

import com.campus.campus_management_system.model.entity.Enrollment;
import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.repository.EnrollmentRepository;
import com.campus.campus_management_system.repository.StudentRepository;
import com.campus.campus_management_system.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    // ТОЗИ МЕТОД ТЪРСИ КОНТРОЛЕРЪТ:
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Студентът не е намерен"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курсът не е намерен"));

        Enrollment enrollment = new Enrollment(student, course);
        // Не сетваме дата ръчно - @PrePersist в модела ще го направи!
        return enrollmentRepository.save(enrollment);
    }
}
