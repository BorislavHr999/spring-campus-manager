package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.model.entity.User;
import com.campus.campus_management_system.repository.StudentRepository;
import com.campus.campus_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Моля, влезте в профила си!");
        }

        String currentUsername = authentication.getName();
        Optional<User> currentUserOpt = userRepository.findByUsername(currentUsername);

        if (currentUserOpt.isPresent()) {
            Student myProfile = currentUserOpt.get().getStudent();
            if (myProfile != null) {
                return ResponseEntity.ok(myProfile);
            }
            return ResponseEntity.status(404).body("Студентският профил не е намерен.");
        }
        return ResponseEntity.status(404).body("Потребителят не е намерен.");
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateMyProfile(Authentication authentication, @RequestBody Student updatedData) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Моля, влезте в профила си!");
        }

        String currentUsername = authentication.getName();
        Optional<User> currentUserOpt = userRepository.findByUsername(currentUsername);

        if (currentUserOpt.isPresent()) {
            Student myProfile = currentUserOpt.get().getStudent();
            if (myProfile != null) {
                myProfile.setFirstName(updatedData.getFirstName());
                myProfile.setLastName(updatedData.getLastName());
                myProfile.setAge(updatedData.getAge());
                myProfile.setFacultyNumber(updatedData.getFacultyNumber());
                
                studentRepository.save(myProfile);
                return ResponseEntity.ok(myProfile);
            }
        }
        return ResponseEntity.status(404).body("Профилът не е намерен.");
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        }
        return ResponseEntity.status(404).body("Студентът не е намерен.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Студентът е изтрит успешно.");
        }
        return ResponseEntity.status(404).body("Студентът не е намерен.");
    }
}