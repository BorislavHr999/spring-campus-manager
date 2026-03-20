package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.model.entity.Enrollment;
import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.model.entity.User;
import com.campus.campus_management_system.repository.CourseRepository;
import com.campus.campus_management_system.repository.EnrollmentRepository;
import com.campus.campus_management_system.repository.StudentRepository;
import com.campus.campus_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository, 
                             UserRepository userRepository,
                             CourseRepository courseRepository,
                             EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(Authentication authentication) {
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername).orElse(null);
        if (user != null && user.getStudent() != null) {
            return ResponseEntity.ok(user.getStudent());
        }
        return ResponseEntity.status(404).body("Профилът не е намерен.");
    }

    @PostMapping("/enroll/{courseId}")
    public ResponseEntity<?> enrollInCourse(@PathVariable Long courseId, Authentication authentication) {
        
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Потребителят не е намерен"));
        
        Student student = user.getStudent();
        if (student == null) {
            return ResponseEntity.badRequest().body("Само студенти могат да се записват за курсове.");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Курсът не е намерен"));

        if (enrollmentRepository.existsByStudentIdAndCourseId(student.getId(), courseId)) {
            return ResponseEntity.badRequest().body("Вече сте записани за този курс!");
        }

        Enrollment enrollment = new Enrollment(student, course);
        enrollmentRepository.save(enrollment);

        return ResponseEntity.ok("Успешно се записахте за курс: " + course.getName());
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudents(Pageable pageable){
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return ResponseEntity.ok(studentPage);
    }
    
    @GetMapping("/{id}/courses")
    public ResponseEntity<?> getStudentCourses(@PathVariable Long id) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(id);
        return ResponseEntity.ok(enrollments);
    }
}