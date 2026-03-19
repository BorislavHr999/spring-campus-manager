package com.campus.campus_management_system.service;

import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.model.entity.Enrollment;
import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.repository.CourseRepository;
import com.campus.campus_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // Взимане на всички студенти (с пагинация и търсене)
    public Page<Student> getAllStudents(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword != null && !keyword.trim().isEmpty()) {
            return studentRepository.searchStudents(keyword, pageable);
        }
        return studentRepository.findAll(pageable);
    }

    // Създаване на студент и закачане на специалностите
    public Student createStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Този имейл вече е регистриран!");
        }
        if (studentRepository.existsByFacultyNumber(student.getFacultyNumber())) {
            throw new RuntimeException("Този факултетен номер вече съществува!");
        }

        return studentRepository.save(student);
    }

    // Редактиране на студент
    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Студентът не е намерен!"));

        existingStudent.setFirstName(studentDetails.getFirstName());
        existingStudent.setLastName(studentDetails.getLastName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setFacultyNumber(studentDetails.getFacultyNumber());

        return studentRepository.save(existingStudent);
    }

    // Изтриване на студент
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
 