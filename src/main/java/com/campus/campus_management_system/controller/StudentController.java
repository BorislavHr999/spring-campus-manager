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
import java.util.Map;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id, Authentication authentication) {
        
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin) {
            return ResponseEntity.status(403).body(Map.of("message", "Отказ! Само администратори могат да трият студенти."));
        }

        try {
            Optional<Student> studentOpt = studentRepository.findById(id);
            if (studentOpt.isPresent()) {
                Student student = studentOpt.get();
                User linkedUser = student.getUser();

                enrollmentRepository.deleteByStudentId(id);

                studentRepository.delete(student);

                if (linkedUser != null) {
                    userRepository.delete(linkedUser);
                }

                return ResponseEntity.ok(Map.of("message", "Студентът и всички негови данни са изтрити успешно!"));
            } else {
                return ResponseEntity.status(404).body(Map.of("message", "Студентът не е намерен!"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Грешка при изтриване: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{studentId}/courses/{courseId}/grade")
    public ResponseEntity<?> updateStudentGrade(
            @PathVariable Long studentId,
            @PathVariable Long courseId,
            @RequestParam Double grade,
            Authentication authentication) {
            
        
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                
        if (!isAdmin) {
            return ResponseEntity.status(403).body(Map.of("message", "Само администратори могат да пишат оценки!"));
        }

        Optional<Enrollment> enrollmentOpt = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        
        if (enrollmentOpt.isPresent()) {
            Enrollment enrollment = enrollmentOpt.get();
            enrollment.setGrade(grade);
            enrollmentRepository.save(enrollment);
            
            return ResponseEntity.ok(Map.of("message", "Оценката е записана успешно!"));
        } else {
            return ResponseEntity.status(404).body(Map.of("message", "Не е намерено записване за този студент и курс."));
        }
    }

    // --- ПОПРАВЕНИТЕ МЕТОДИ ЗА ЗАПАЗВАНЕ И РЕДАКЦИЯ ---

    @PostMapping 
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        // Използваме вече дефинирания studentRepository
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        // Задаваме правилното ID на обекта, за да презапише съществуващия, а не да създаде нов
        student.setId(id);
        return ResponseEntity.ok(studentRepository.save(student));
    }
}