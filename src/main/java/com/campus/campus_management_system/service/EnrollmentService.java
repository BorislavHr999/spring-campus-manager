package com.campus.campus_management_system.service;

import com.campus.campus_management_system.model.entity.Enrollment;
import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public void saveEnrollment(Student student, Course course) {
        Enrollment enrollment = new Enrollment(student, course);
        
        enrollmentRepository.save(enrollment);
    }
}
