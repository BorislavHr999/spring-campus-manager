package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.model.entity.Enrollment;
import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.model.entity.User;
import com.campus.campus_management_system.repository.CourseRepository;
import com.campus.campus_management_system.repository.EnrollmentRepository;
import com.campus.campus_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public EnrollmentController(EnrollmentRepository enrollmentRepository, 
                                CourseRepository courseRepository, 
                                UserRepository userRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/enroll/{courseId}")
    public ResponseEntity<?> enrollInCourse(@PathVariable Long courseId, Authentication authentication) {
        
        if (authentication == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Трябва да сте влезли в профила си, за да се запишете!"));
        }

        String username = authentication.getName();
        Optional<User> userOpt = userRepository.findByUsername(username);
        
        if (userOpt.isEmpty() || userOpt.get().getStudent() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Вашият акаунт няма свързан студентски профил!"));
        }
        
        Student student = userOpt.get().getStudent();

        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Този курс не съществува!"));
        }
        Course course = courseOpt.get();

        if (enrollmentRepository.existsByStudentIdAndCourseId(student.getId(), courseId)) {
            return ResponseEntity.badRequest().body(Map.of("message", "Вече сте записани за курс: " + course.getName()));
        }

        Enrollment newEnrollment = new Enrollment();
        newEnrollment.setStudent(student);
        newEnrollment.setCourse(course);
        
        enrollmentRepository.save(newEnrollment);

        return ResponseEntity.ok(Map.of("message", "Успешно се записахте за курс: " + course.getName()));
    }
}
