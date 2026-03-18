package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Enrollment;
import com.campus.campus_management_system.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public Enrollment saveEnrollment(@RequestParam Long studentId, @RequestParam Long courseId) {
        // Вече типовете съвпадат (Long, Long)
        return enrollmentService.enrollStudent(studentId, courseId);
    }
}
